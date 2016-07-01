package model

import (
	"fmt"
	"strings"

	"github.com/taskcluster/taskcluster-client-java/codegenerator/utils"
)

//////////////////////////////////////////////////////////////////
//
// From: http://schemas.taskcluster.net/base/v1/api-reference.json
//
//////////////////////////////////////////////////////////////////

// API represents the HTTP interface of a TaskCluster service
type API struct {
	BaseURL     string      `json:"baseUrl"`
	Description string      `json:"description"`
	Entries     []APIEntry  `json:"entries"`
	Title       string      `json:"title"`
	Version     interface{} `json:"version"`
	Schema      string      `json:"$schema"`

	apiDef *APIDefinition
}

func (api *API) String() string {
	var result string = fmt.Sprintf(
		"Version     = '%v'\n"+
			"Schema      = '%v'\n"+
			"Title       = '%v'\n"+
			"Description = '%v'\n"+
			"Base URL    = '%v'\n",
		api.Version, api.Schema, api.Title, api.Description, api.BaseURL,
	)
	for i, entry := range api.Entries {
		result += fmt.Sprintf("Entry %-6v=\n%v", i, entry.String())
	}
	return result
}

func (api *API) postPopulate(apiDef *APIDefinition) {

	// make sure each entry defined for this API has a unique generated method name
	methods := make(map[string]bool)

	for i := range api.Entries {
		api.Entries[i].Parent = api
		api.Entries[i].MethodName = utils.NormaliseLower(api.Entries[i].Name, methods)
		api.Entries[i].postPopulate(apiDef)
	}
}

func (api *API) generateAPICode(apiName string) string {
	className := strings.Title(apiName)
	packageName := strings.ToLower(apiName)
	content := `package org.mozilla.taskcluster.client.` + packageName + `;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

`
	comment := "/**\n"
	if api.Description != "" {
		comment += utils.Indent(api.Description, " * ", true)
	}
	if len(comment) >= 1 && comment[len(comment)-1:] != "\n" {
		comment += "\n"
	}
	comment += " *\n"
	comment += fmt.Sprintf(" * See: %v\n", api.apiDef.DocRoot)
	comment += " */\n"
	content += comment
	content += "public class " + className + ` extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "` + api.BaseURL + `";

    public ` + className + `(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public ` + className + `(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public ` + className + `(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public ` + className + `(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public ` + className + `(String baseURL) {
        super(baseURL);
    }

    public ` + className + `() {
        super(defaultBaseURL);
    }
`
	for _, entry := range api.Entries {
		content += entry.generateAPICode(apiName)
	}
	content += "}"
	return content
}

func (api *API) setAPIDefinition(apiDef *APIDefinition) {
	api.apiDef = apiDef
}

type APIEntry struct {
	Args        []string   `json:"args"`
	Description string     `json:"description"`
	Input       string     `json:"input"`
	Method      string     `json:"method"`
	Name        string     `json:"name"`
	Query       []string   `json:"query"`
	Route       string     `json:"route"`
	Scopes      [][]string `json:"scopes"`
	Stability   string     `json:"stability"`
	Output      string     `json:"output"`
	Title       string     `json:"title"`
	Type        string     `json:"type"`

	MethodName string
	Parent     *API
}

// Add entry.Input and entry.Output to schemaURLs, if they are set
func (entry *APIEntry) postPopulate(apiDef *APIDefinition) {
	for _, v := range []string{
		entry.Input,
		entry.Output,
	} {
		if x := &entry.Parent.apiDef.schemaURLs; v != "" {
			*x = append(*x, v)
		}
	}
}

func (entry *APIEntry) String() string {
	return fmt.Sprintf(
		"    Entry Type        = '%v'\n"+
			"    Entry Method      = '%v'\n"+
			"    Entry Route       = '%v'\n"+
			"    Entry Args        = '%v'\n"+
			"    Entry Query        = '%v'\n"+
			"    Entry Name        = '%v'\n"+
			"    Entry Stability   = '%v'\n"+
			"    Entry Scopes      = '%v'\n"+
			"    Entry Input       = '%v'\n"+
			"    Entry Output      = '%v'\n"+
			"    Entry Title       = '%v'\n"+
			"    Entry Description = '%v'\n",
		entry.Type, entry.Method, entry.Route, entry.Args,
		entry.Query, entry.Name, entry.Stability, entry.Scopes,
		entry.Input, entry.Output, entry.Title, entry.Description,
	)
}

func (entry *APIEntry) generateAPICode(apiName string) string {
	comment := "\n    /**\n"
	if entry.Description != "" {
		comment += utils.Indent(entry.Description, "     * ", true)
	}
	if len(comment) >= 1 && comment[len(comment)-1:] != "\n" {
		comment += "\n"
	}
	comment += requiredScopesComment(entry.Scopes)
	comment += "     *\n"
	comment += fmt.Sprintf("     * See %v#%v\n", entry.Parent.apiDef.DocRoot, entry.Name)
	comment += "     */\n"
	inputParams := ""
	if len(entry.Args) > 0 {
		inputParams += "String " + strings.Join(entry.Args, ", String ")
	}

	apiArgsPayload := "null"
	if entry.Input != "" {
		apiArgsPayload = "payload"
		p := entry.Parent.apiDef.schemas.SubSchema(entry.Input).TypeName + " payload"
		if inputParams == "" {
			inputParams = p
		} else {
			inputParams += ", " + p
		}
	}

	requestType := "EmptyPayload"
	if entry.Input != "" {
		requestType = entry.Parent.apiDef.schemas.SubSchema(entry.Input).TypeName
	}
	responseType := "EmptyPayload"
	if entry.Output != "" {
		responseType = entry.Parent.apiDef.schemas.SubSchema(entry.Output).TypeName
	}
	returnType := "CallSummary<" + requestType + ", " + responseType + ">"

	content := comment
	content += "    public " + returnType + " " + entry.MethodName + "(" + inputParams + ") throws APICallFailure {\n"
	content += "        return apiCall(" + apiArgsPayload + ", \"" + strings.ToUpper(entry.Method) + "\", \"" + strings.Replace(strings.Replace(entry.Route, "<", "\" + uriEncode(", -1), ">", ") + \"", -1) + "\", " + responseType + ".class);\n"
	content += "    }\n"
	// can remove any code that added an empty string to another string
	return strings.Replace(content, ` + ""`, "", -1)
}

func requiredScopesComment(scopes [][]string) string {
	if len(scopes) == 0 {
		return ""
	}
	comment := "     *\n"
	comment += "     * Required scopes:\n"
	comment += "     *\n"
	switch len(scopes) {
	case 0:
	case 1:
		comment += "     *   * " + strings.Join(scopes[0], ", and\n     *   * ") + "\n"
	default:
		lines := make([]string, len(scopes))
		for i, j := range scopes {
			switch len(j) {
			case 0:
			case 1:
				lines[i] = "     *   * " + j[0]
			default:
				lines[i] = "     *   * (" + strings.Join(j, " and ") + ")"
			}
		}
		comment += strings.Join(lines, ", or\n") + "\n"
	}
	return comment
}
