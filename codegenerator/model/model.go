package model

//go:generate generatemodel -u http://references.taskcluster.net/manifest.json -f apis.json -o ../.. -m model-data.txt

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"os"
	"path/filepath"
	"sort"
	"strings"

	"github.com/taskcluster/jsonschema2go"
	"github.com/taskcluster/taskcluster-client-java/codegenerator/utils"
	"github.com/xeipuuv/gojsonschema"
)

var (
	apiDefs []APIDefinition
	err     error
)

type SortedAPIDefs []APIDefinition

// needed so that SortedAPIDefs can implement sort.Interface
func (a SortedAPIDefs) Len() int           { return len(a) }
func (a SortedAPIDefs) Swap(i, j int)      { a[i], a[j] = a[j], a[i] }
func (a SortedAPIDefs) Less(i, j int) bool { return a[i].URL < a[j].URL }

type APIModel interface {
	String() string
	postPopulate(apiDef *APIDefinition)
	generateAPICode(name string) string
	setAPIDefinition(apiDef *APIDefinition)
}

// APIDefinition represents the definition of a REST API, comprising of the URL to the defintion
// of the API in json format, together with a URL to a json schema to validate the definition
type APIDefinition struct {
	URL            string `json:"url"`
	Name           string `json:"name"`
	DocRoot        string `json:"docroot"`
	Data           APIModel
	schemaURLs     []string
	schemas        *jsonschema2go.SchemaSet
	PackageName    string
	ExampleVarName string
	PackagePath    string
	SchemaURL      string
}

func (a *APIDefinition) generateAPICode() string {
	return a.Data.generateAPICode(a.Name)
}

func (apiDef *APIDefinition) loadJson(reader io.Reader) {
	b := new(bytes.Buffer)
	_, err := b.ReadFrom(reader)
	utils.ExitOnFail(err)
	data := b.Bytes()
	f := new(interface{})
	err = json.Unmarshal(data, f)
	utils.ExitOnFail(err)
	schema := (*f).(map[string]interface{})["$schema"].(string)
	apiDef.SchemaURL = schema
	var m APIModel
	switch schema {
	case "http://schemas.taskcluster.net/base/v1/api-reference.json#":
		m = new(API)
	case "http://schemas.taskcluster.net/base/v1/exchanges-reference.json#":
		m = new(Exchange)
	default:
		panic(fmt.Errorf("Do not know how to handle API with schema %q", schema))
	}
	err = json.Unmarshal(data, m)
	utils.ExitOnFail(err)
	m.setAPIDefinition(apiDef)
	m.postPopulate(apiDef)
	apiDef.Data = m
}

// LoadAPIs takes care of reading all json files and performing elementary
// processing of the data, such as assigning unique type names to entities
// which will be translated to go types.
//
// Data is unmarshaled into objects (or instances of go types) and then
// postPopulate is called on the objects. This in turn triggers further reading
// of json files and unmarshalling where schemas refer to other schemas.
//
// When LoadAPIs returns, all json schemas and sub schemas should have been
// read and unmarhsalled into go objects.
func LoadAPIs(apiManifestUrl, supplementaryDataFile string) []APIDefinition {
	resp, err := http.Get(apiManifestUrl)
	if err != nil {
		fmt.Printf("Could not download api manifest from url: '%v'!\n", apiManifestUrl)
	}
	supDataReader, err := os.Open(supplementaryDataFile)
	if err != nil {
		fmt.Printf("Could not load supplementary data json file: '%v'!\n", supplementaryDataFile)
	}
	utils.ExitOnFail(err)
	apiManifestDecoder := json.NewDecoder(resp.Body)
	apiMan := make(map[string]string)
	err = apiManifestDecoder.Decode(&apiMan)
	utils.ExitOnFail(err)
	supDataDecoder := json.NewDecoder(supDataReader)
	err = supDataDecoder.Decode(&apiDefs)
	utils.ExitOnFail(err)
	sort.Sort(SortedAPIDefs(apiDefs))

	// build up apis based on data in *both* data sources
	for i := range apiMan {
		// seach for apiMan[i] in apis
		k := sort.Search(len(apiDefs), func(j int) bool {
			return apiDefs[j].URL >= apiMan[i]
		})
		if k < len(apiDefs) && apiDefs[k].URL == apiMan[i] {
			// url is present in supplementary data
			apiDefs[k].Name = i
		} else {
			fmt.Printf(
				"\nFATAL: Manifest from url '%v' contains key '%v' with url '%v', but this url does not exist in supplementary data file '%v', therefore exiting...\n\n",
				apiManifestUrl, i, apiMan[i], supplementaryDataFile)
			os.Exit(64)
		}
	}
	for i := range apiDefs {
		if apiDefs[i].Name == "" {
			fmt.Printf(
				"\nFATAL: Manifest from url '%v' does not contain url '%v' which does exist in supplementary data file '%v', therefore exiting...\n\n",
				apiManifestUrl, apiDefs[i].URL, supplementaryDataFile)
			os.Exit(65)
		}
	}
	for i := range apiDefs {
		var resp *http.Response
		resp, err = http.Get(apiDefs[i].URL)
		utils.ExitOnFail(err)
		defer resp.Body.Close()
		apiDefs[i].loadJson(resp.Body)

		// check that the json schema is valid!
		validateJson(apiDefs[i].SchemaURL, apiDefs[i].URL)
	}
	return apiDefs
}

func validateJson(schemaUrl, docUrl string) {
	schemaLoader := gojsonschema.NewReferenceLoader(schemaUrl)
	docLoader := gojsonschema.NewReferenceLoader(docUrl)
	result, err := gojsonschema.Validate(schemaLoader, docLoader)
	utils.ExitOnFail(err)
	if result.Valid() {
		fmt.Printf("Document '%v' is valid against '%v'.\n", docUrl, schemaUrl)
	} else {
		fmt.Printf("Document '%v' is INVALID against '%v'.\n", docUrl, schemaUrl)
		for _, desc := range result.Errors() {
			fmt.Println("")
			fmt.Printf("- %s\n", desc)
		}
		// os.Exit(70)
	}
}

// GenerateCode takes the objects loaded into memory in LoadAPIs
// and writes them out as go code.
func GenerateCode(goOutputDir, modelData string) {
	for i := range apiDefs {
		apiDefs[i].PackageName = strings.ToLower(apiDefs[i].Name)
		apiDefs[i].PackagePath = filepath.Join(goOutputDir, "src", "main", "java", "org", "mozilla", "taskcluster", "client", apiDefs[i].PackageName)
		err = os.MkdirAll(apiDefs[i].PackagePath, 0755)
		utils.ExitOnFail(err)
		content := `// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// ` + apiDefs[i].URL + `
`
		generatePayloadTypes(&apiDefs[i])
		content += apiDefs[i].generateAPICode()
		className := strings.Title(apiDefs[i].Name)
		sourceFile := filepath.Join(apiDefs[i].PackagePath, className+".java")
		fmt.Println("Generating source code " + sourceFile + "...")
		utils.WriteStringToFile(content, sourceFile)
		utils.ExitOnFail(err)
	}

	content := "The following file is an auto-generated static dump of the API models at time of code generation.\n"
	content += "It is provided here for reference purposes, but is not used by any code.\n"
	content += "\n"
	for i := range apiDefs {
		content += utils.Underline(apiDefs[i].URL)
		content += apiDefs[i].Data.String() + "\n\n"
		for _, url := range apiDefs[i].schemas.SortedSanitizedURLs() {
			content += (utils.Underline(url))
			content += apiDefs[i].schemas.SubSchema(url).String() + "\n\n"
		}
	}
	utils.WriteStringToFile(content, modelData)
}

// This is where we generate nested and compoound types in go to represent json payloads
// which are used as inputs and outputs for the REST API endpoints, and also for Pulse
// message bodies for the Exchange APIs.
// Returns the generated code content, and a map of keys of extra packages to import, e.g.
// a generated type might use time.Time, so if not imported, this would have to be added.
// using a map of strings -> bool to simulate a set - true => include
func generatePayloadTypes(apiDef *APIDefinition) {

	job := &jsonschema2go.Job{
		Package:     apiDef.PackageName,
		URLs:        apiDef.schemaURLs,
		SkipCodeGen: true,
		TypeNameGenerator: func(name string, exported bool, blacklist map[string]bool) (identifier string) {
			return utils.Normalise(name, blacklist)
		},
		MemberNameGenerator: func(name string, exported bool, blacklist map[string]bool) (identifier string) {
			return utils.NormaliseLower(name, blacklist)
		},
	}
	result, err := job.Execute()
	utils.ExitOnFail(err)

	apiDef.schemas = result.SchemaSet

	for _, i := range apiDef.schemas.SortedSanitizedURLs() {
		extraPackages := make(map[string]bool)
		content := "package org.mozilla.taskcluster.client." + strings.ToLower(apiDef.PackageName) + ";\n"
		content += "\n"
		s := JsonSubSchema(*apiDef.schemas.SubSchema(i))
		typeClass, typeComment, typ := (&s).TypeDefinition(0, extraPackages)
		if typeClass != "" {
			className := typ
			if strings.HasSuffix(typ, "[]") {
				className = typ[:len(typ)-2]
				apiDef.schemas.SubSchema(i).TypeName = typ
			}
			if len(extraPackages) > 0 {
				for pckage := range extraPackages {
					content += "import " + pckage + ";\n"
				}
				content += "\n"
			}
			content += typeComment
			content += typeClass[1:]
			utils.WriteStringToFile(content, filepath.Join(apiDef.PackagePath, className+".java"))
		} else {
			apiDef.schemas.SubSchema(i).TypeName = typ
		}
	}
}
