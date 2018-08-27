package model

import (
	"encoding/json"
	"errors"
	"fmt"
	"strings"

	"github.com/kr/text"
	"github.com/taskcluster/taskcluster-client-java/codegenerator/utils"
	tcurls "github.com/taskcluster/taskcluster-lib-urls"
)

//////////////////////////////////////////////////////////////////
//
// From: http://schemas.taskcluster.net/base/v1/api-reference.json
//
//////////////////////////////////////////////////////////////////

// API represents the HTTP interface of a Taskcluster service
type API struct {
	*APIReferenceFile
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
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

`
	comment := "/**\n"
	if api.Description != "" {
		comment += utils.Indent(api.Description, " * ", true)
	}
	if len(comment) >= 1 && comment[len(comment)-1:] != "\n" {
		comment += "\n"
	}
	comment += " *\n"
	comment += fmt.Sprintf(" * @see \"[%v API Documentation](%v)\"\n", className, api.apiDef.DocRoot)
	comment += " */\n"
	content += comment
	content += "public class " + className + ` extends TaskclusterRequestHandler {

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
	*Entry
	MethodName string
	Parent     *API
	InputURL   string
	OutputURL  string
}

// Add entry.Input and entry.Output to schemaURLs, if they are set
func (entry *APIEntry) postPopulate(apiDef *APIDefinition) {
	if x := &entry.Parent.apiDef.schemaURLs; entry.Input != "" {
		entry.InputURL = tcurls.Schema("https://taskcluster.net", entry.Parent.ServiceName, entry.Input)
		*x = append(*x, entry.InputURL)
	}
	if x := &entry.Parent.apiDef.schemaURLs; entry.Output != "" {
		entry.OutputURL = tcurls.Schema("https://taskcluster.net", entry.Parent.ServiceName, entry.Output)
		*x = append(*x, entry.OutputURL)
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
			"    Entry InputURL    = '%v'\n"+
			"    Entry Output      = '%v'\n"+
			"    Entry OutputURL   = '%v'\n"+
			"    Entry Title       = '%v'\n"+
			"    Entry Description = '%v'\n",
		entry.Type, entry.Method, entry.Route, entry.Args,
		entry.Query, entry.Name, entry.Stability, &entry.Scopes,
		entry.Input, entry.InputURL, entry.Output, entry.OutputURL,
		entry.Title, entry.Description,
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
	comment += requiredScopesComment(&entry.Scopes)
	comment += "     *\n"
	comment += fmt.Sprintf("     * @see \"[%v API Documentation](%v#%v)\"\n", entry.Title, entry.Parent.apiDef.DocRoot, entry.Name)
	comment += "     */\n"
	inputParams := ""
	if len(entry.Args) > 0 {
		inputParams += "String " + strings.Join(entry.Args, ", String ")
	}

	apiArgsPayload := "null"
	if entry.InputURL != "" {
		apiArgsPayload = "payload"
		p := entry.Parent.apiDef.schemas.SubSchema(entry.InputURL).TypeName + " payload"
		if inputParams == "" {
			inputParams = p
		} else {
			inputParams += ", " + p
		}
	}

	requestType := "EmptyPayload"
	if entry.InputURL != "" {
		requestType = entry.Parent.apiDef.schemas.SubSchema(entry.InputURL).TypeName
	}
	responseType := "EmptyPayload"
	if entry.OutputURL != "" {
		responseType = entry.Parent.apiDef.schemas.SubSchema(entry.OutputURL).TypeName
	}
	returnType := "CallSummary<" + requestType + ", " + responseType + ">"

	content := comment
	content += "    public " + returnType + " " + entry.MethodName + "(" + inputParams + ") throws APICallFailure {\n"
	content += "        return apiCall(" + apiArgsPayload + ", \"" + strings.ToUpper(entry.Method) + "\", \"" + strings.Replace(strings.Replace(entry.Route, "<", "\" + uriEncode(", -1), ">", ") + \"", -1) + "\", " + responseType + ".class);\n"
	content += "    }\n"
	// can remove any code that added an empty string to another string
	return strings.Replace(content, ` + ""`, "", -1)
}

func requiredScopesComment(scopes *ScopeExpressionTemplate) string {
	if scopes.Type == "" {
		return ""
	}
	comment := "\n"
	comment += "Required scopes:\n"
	comment += text.Indent(scopes.String()+"\n", "  ")
	return text.Indent(comment, "     * ")
}

func (scopes *ScopeExpressionTemplate) String() string {
	switch scopes.Type {
	case "":
		return ""
	case "AllOf":
		return scopes.AllOf.String()
	case "AnyOf":
		return scopes.AnyOf.String()
	case "ForEachIn":
		return scopes.ForEachIn.String()
	case "IfThen":
		return scopes.IfThen.String()
	case "RequiredScope":
		return scopes.RequiredScope.String()
	default:
		panic(fmt.Sprintf("Internal error - did not recognise scope form '%v'", scopes.Type))
	}
}

func (allOf *AllOf) String() string {
	if allOf == nil {
		return "WARNING: NIL AllOf"
	}
	switch len(allOf.AllOf) {
	case 0:
		return ""
	case 1:
		return allOf.AllOf[0].String()
	}
	var desc string
	for _, exp := range allOf.AllOf {
		x := text.Indent(exp.String(), "  ")
		if len(x) >= 2 {
			desc += "\n" + "* " + x[2:]
		}
	}
	return "All of:" + desc
}

func (anyOf *AnyOf) String() string {
	if len(anyOf.AnyOf) == 0 {
		return "AnyOf empty set - INVALID"
	}
	if len(anyOf.AnyOf) == 1 {
		return anyOf.AnyOf[0].String()
	}
	var desc string
	for _, exp := range anyOf.AnyOf {
		x := text.Indent(exp.String(), "  ")
		if len(x) >= 2 {
			desc += "\n" + "- " + x[2:]
		}
	}
	return "Any of:" + desc
}

func (forEachIn *ForEachIn) String() string {
	return "For " + forEachIn.For + " in " + forEachIn.In + " each " + forEachIn.Each
}

func (ifThen *IfThen) String() string {
	return "If " + ifThen.If + ":\n" + text.Indent(ifThen.Then.String(), "  ")
}

func (rs *RequiredScope) String() string {
	return string(*rs)
}

// MarshalJSON calls json.RawMessage method of the same name. Required since
// ScopeExpressionTemplate is of type json.RawMessage...
func (this *ScopeExpressionTemplate) MarshalJSON() ([]byte, error) {
	return (this.RawMessage).MarshalJSON()
}

// UnmarshalJSON identifies the data structure at runtime, and unmarshals in
// the appropriate type
func (this *ScopeExpressionTemplate) UnmarshalJSON(data []byte) error {
	if this == nil {
		return errors.New("ScopeExpressionTemplate: UnmarshalJSON on nil pointer")
	}
	this.RawMessage = append((this.RawMessage)[0:0], data...)
	var tempObj interface{}
	err := json.Unmarshal(this.RawMessage, &tempObj)
	if err != nil {
		panic("Internal error: " + err.Error())
	}
	switch t := tempObj.(type) {
	case string:
		this.Type = "RequiredScope"
		this.RequiredScope = new(RequiredScope)
		*(this.RequiredScope) = RequiredScope(t)
	case map[string]interface{}:
		j, err := json.Marshal(t)
		if err != nil {
			panic("Internal error: " + err.Error())
		}
		if _, exists := t["AnyOf"]; exists {
			this.Type = "AnyOf"
			this.AnyOf = new(AnyOf)
			err = json.Unmarshal(j, this.AnyOf)
		}
		if _, exists := t["AllOf"]; exists {
			this.Type = "AllOf"
			this.AllOf = new(AllOf)
			err = json.Unmarshal(j, this.AllOf)
		}
		if _, exists := t["if"]; exists {
			this.Type = "IfThen"
			this.IfThen = new(IfThen)
			err = json.Unmarshal(j, this.IfThen)
		}
		if _, exists := t["for"]; exists {
			this.Type = "ForEachIn"
			this.ForEachIn = new(ForEachIn)
			err = json.Unmarshal(j, this.ForEachIn)
		}
		if err != nil {
			panic("Internal error: " + err.Error())
		}
	// for old style scopesets [][]string (normal disjunctive form)
	case []interface{}:
		this.Type = "AnyOf"
		this.AnyOf = &AnyOf{
			AnyOf: make([]ScopeExpressionTemplate, len(t), len(t)),
		}
		for i, j := range t {
			allOf := j.([]interface{})
			this.AnyOf.AnyOf[i] = ScopeExpressionTemplate{
				Type: "AllOf",
				AllOf: &AllOf{
					AllOf: make([]ScopeExpressionTemplate, len(allOf), len(allOf)),
				},
			}
			for k, l := range allOf {
				rs := RequiredScope(l.(string))
				this.AnyOf.AnyOf[i].AllOf.AllOf[k] = ScopeExpressionTemplate{
					Type:          "RequiredScope",
					RequiredScope: &rs,
				}
			}
		}
	default:
		panic(fmt.Sprintf("Internal error: unrecognised type %T", t))
	}
	return nil
}
