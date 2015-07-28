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

type API struct {
	Version     interface{} `json:"version"`
	Title       string      `json:"title"`
	Description string      `json:"description"`
	BaseURL     string      `json:"baseUrl"`
	Entries     []APIEntry  `json:"entries"`

	apiDef *APIDefinition
}

func (api *API) String() string {
	var result string = fmt.Sprintf(
		"Version     = '%v'\n"+
			"Title       = '%v'\n"+
			"Description = '%v'\n"+
			"Base URL    = '%v'\n",
		api.Version, api.Title, api.Description, api.BaseURL)
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
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

`
	comment := "/**\n"
	if api.Description != "" {
		comment += utils.Indent(api.Description, " * ")
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

    public ` + className + `(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public ` + className + `(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
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
	Type        string     `json:"type"`
	Method      string     `json:"method"`
	Route       string     `json:"route"`
	Args        []string   `json:"args"`
	Name        string     `json:"name"`
	Scopes      [][]string `json:"scopes"`
	Input       string     `json:"input"`
	Output      string     `json:"output"`
	Title       string     `json:"title"`
	Description string     `json:"description"`

	MethodName string
	Parent     *API
}

func (entry *APIEntry) postPopulate(apiDef *APIDefinition) {
	if entry.Input != "" {
		entry.Parent.apiDef.cacheJsonSchema(&entry.Input)
		entry.Parent.apiDef.schemas[entry.Input].IsInputSchema = true
	}
	if entry.Output != "" {
		entry.Parent.apiDef.cacheJsonSchema(&entry.Output)
		entry.Parent.apiDef.schemas[entry.Output].IsOutputSchema = true
	}
}

func (entry *APIEntry) String() string {
	return fmt.Sprintf(
		"    Entry Type        = '%v'\n"+
			"    Entry Method      = '%v'\n"+
			"    Entry Route       = '%v'\n"+
			"    Entry Args        = '%v'\n"+
			"    Entry Name        = '%v'\n"+
			"    Entry Scopes      = '%v'\n"+
			"    Entry Input       = '%v'\n"+
			"    Entry Output      = '%v'\n"+
			"    Entry Title       = '%v'\n"+
			"    Entry Description = '%v'\n",
		entry.Type, entry.Method, entry.Route, entry.Args,
		entry.Name, entry.Scopes, entry.Input, entry.Output,
		entry.Title, entry.Description)
}

func (entry *APIEntry) generateAPICode(apiName string) string {
	comment := "\n    /**\n"
	if entry.Description != "" {
		comment += utils.Indent(entry.Description, "     * ")
	}
	if len(comment) >= 1 && comment[len(comment)-1:] != "\n" {
		comment += "\n"
	}
	comment += "     *\n"
	comment += fmt.Sprintf("     * See %v/#%v\n", entry.Parent.apiDef.DocRoot, entry.Name)
	comment += "     */\n"
	inputParams := ""
	if len(entry.Args) > 0 {
		inputParams += "String " + strings.Join(entry.Args, ", String ")
	}

	apiArgsPayload := "null"
	if entry.Input != "" {
		apiArgsPayload = "payload"
		p := entry.Parent.apiDef.schemas[entry.Input].TypeName + " payload"
		if inputParams == "" {
			inputParams = p
		} else {
			inputParams += ", " + p
		}
	}

	requestType := "EmptyPayload"
	if entry.Input != "" {
		requestType = entry.Parent.apiDef.schemas[entry.Input].TypeName
	}
	responseType := "EmptyPayload"
	if entry.Output != "" {
		responseType = entry.Parent.apiDef.schemas[entry.Output].TypeName
		if entry.Parent.apiDef.schemas[entry.Output].Type != nil && *entry.Parent.apiDef.schemas[entry.Output].Type == "array" {
			responseType += "[]"
		}
	}
	returnType := "CallSummary<" + requestType + ", " + responseType + ">"

	content := comment
	content += "    public " + returnType + " " + entry.MethodName + "(" + inputParams + ") throws APICallFailure {\n"
	content += "        return apiCall(" + apiArgsPayload + ", \"" + strings.ToUpper(entry.Method) + "\", \"" + strings.Replace(strings.Replace(entry.Route, "<", "\" + ", -1), ">", " + \"", -1) + "\", " + responseType + ".class);\n"
	content += "    }\n"
	return content
}
