package model

import (
	"encoding/json"
	"fmt"
	"github.com/taskcluster/taskcluster-client-java/codegenerator/utils"
	"reflect"
	"sort"
	"strconv"
	"strings"
)

type (
	// Note that all members are backed by pointers, so that nil value can signify non-existence.
	// Otherwise we could not differentiate whether a zero value is non-existence or actually the
	// zero value. For example, if a bool is false, we don't know if it was explictly set to false
	// in the json we read, or whether it was not given. Unmarshaling into a pointer means pointer
	// will be nil pointer if it wasn't read, or a pointer to true/false if it was read from json.
	JsonSubSchema struct {
		AdditionalItems      *bool                 `json:"additionalItems"`
		AdditionalProperties *AdditionalProperties `json:"additionalProperties"`
		AllOf                Items                 `json:"allOf"`
		AnyOf                Items                 `json:"anyOf"`
		Default              interface{}           `json:"default"`
		Description          *string               `json:"description"`
		Enum                 interface{}           `json:"enum"`
		Format               *string               `json:"format"`
		ID                   *string               `json:"id"`
		Items                *JsonSubSchema        `json:"items"`
		Maximum              *int                  `json:"maximum"`
		MaxLength            *int                  `json:"maxLength"`
		Minimum              *int                  `json:"minimum"`
		MinLength            *int                  `json:"minLength"`
		OneOf                Items                 `json:"oneOf"`
		Pattern              *string               `json:"pattern"`
		Properties           *Properties           `json:"properties"`
		Ref                  *string               `json:"$ref"`
		Required             []string              `json:"required"`
		Schema               *string               `json:"$schema"`
		Title                *string               `json:"title"`
		Type                 *string               `json:"type"`

		// non-json fields used for sorting/tracking
		TypeName       string
		IsInputSchema  bool
		IsOutputSchema bool
		SourceURL      string
		RefSubSchema   *JsonSubSchema
		APIDefinition  *APIDefinition
	}

	Items []JsonSubSchema

	Properties struct {
		Properties          map[string]*JsonSubSchema
		SortedPropertyNames []string
	}

	AdditionalProperties struct {
		Boolean    *bool
		Properties *JsonSubSchema
	}
)

func (subSchema JsonSubSchema) String() string {
	result := ""
	result += describe("Additional Items", subSchema.AdditionalItems)
	result += describe("Additional Properties", subSchema.AdditionalProperties)
	result += describe("All Of", subSchema.AllOf)
	result += describe("Any Of", subSchema.AnyOf)
	result += describe("Default", subSchema.Default)
	result += describe("Description", subSchema.Description)
	result += describe("Enum", subSchema.Enum)
	result += describe("Format", subSchema.Format)
	result += describe("ID", subSchema.ID)
	result += describeList("Items", subSchema.Items)
	result += describe("Maximum", subSchema.Maximum)
	result += describe("MaxLength", subSchema.MaxLength)
	result += describe("Minimum", subSchema.Minimum)
	result += describe("MinLength", subSchema.MinLength)
	result += describeList("OneOf", subSchema.OneOf)
	result += describe("Pattern", subSchema.Pattern)
	result += describeList("Properties", subSchema.Properties)
	result += describe("Ref", subSchema.Ref)
	result += describe("Required", subSchema.Required)
	result += describe("Schema", subSchema.Schema)
	result += describe("Title", subSchema.Title)
	result += describe("Type", subSchema.Type)
	result += describe("TypeName", &subSchema.TypeName)
	result += describe("IsInputSchema", &subSchema.IsInputSchema)
	result += describe("IsOutputSchema", &subSchema.IsOutputSchema)
	result += describe("SourceURL", &subSchema.SourceURL)
	return result
}

func (jsonSubSchema *JsonSubSchema) TypeDefinition(level int, fromArray bool, extraPackages map[string]bool, rawMessageTypes map[string]bool) (string, map[string]bool, map[string]bool, string) {
	content := ""
	if level == 0 && !fromArray {
		content += "/**\n"
		if d := jsonSubSchema.Description; d != nil {
			if desc := *d; desc != "" {
				content += utils.Indent(desc, "* ")
			}
			if content[len(content)-1:] != "\n" {
				content += "\n"
			}
		}
		if url := jsonSubSchema.SourceURL; url != "" {
			content += "*\n* See " + url + "\n"
		}
		content += "*/\n"
	}
	typ := "Object"
	if p := jsonSubSchema.Type; p != nil {
		typ = *p
	}
	if p := jsonSubSchema.RefSubSchema; p != nil {
		typ = p.TypeName
	}
	switch typ {
	case "array":
		if jsonType := jsonSubSchema.Items.Type; jsonType != nil {
			var newType string
			newType, extraPackages, rawMessageTypes, typ = jsonSubSchema.Items.TypeDefinition(level, true, extraPackages, rawMessageTypes)
			if level == 0 {
				if typ == "" {
					content += newType
				}
			} else {
				typ = ""
				content += newType + "[]"
			}
		}

	case "object":
		if s := jsonSubSchema.Properties; s != nil {
			typ = "" // strings.Title(jsonSubSchema.TypeName)
			def := fmt.Sprintf("class " + strings.Title(jsonSubSchema.TypeName) + " {\n")
			for _, j := range s.SortedPropertyNames {
				// recursive call to build go types inside structs
				var subType string
				subType, extraPackages, rawMessageTypes, _ = s.Properties[j].TypeDefinition(level+1, false, extraPackages, rawMessageTypes)
				// comment the struct member with the description from the json
				if d := s.Properties[j].Description; d != nil {
					def += "\n" + utils.Comment(*d, strings.Repeat("    ", level+1))
				}
				// struct member name and type, as part of struct definition
				def += fmt.Sprintf(strings.Repeat("    ", level+1)+"public %v %v;\n", subType, s.Properties[j].TypeName)
			}
			def += strings.Repeat("    ", level) + "}"
			if level == 0 {
				def = "public " + def
			} else {
				def += "\n\n" + strings.Repeat("    ", level) + "public " + strings.Title(jsonSubSchema.TypeName)
			}
			content += def
		} else {
			typ = "Object"
		}
	case "number":
		typ = "int"
	case "integer":
		typ = "int"
	case "boolean":
		typ = "boolean"
	// json type string maps to go type string, so only need to test case of when
	// string is a json date-time, so we can convert to go type time.Time...
	case "string":
		if f := jsonSubSchema.Format; f != nil && *f == "date-time" {
			typ = "Date"
		} else {
			typ = "String"
		}
	}
	switch typ {
	case "Date":
		extraPackages["java.util.Date"] = true
	case "json.RawMessage":
		extraPackages["encoding/json"] = true
	case "map[string]json.RawMessage":
		extraPackages["encoding/json"] = true
	}
	content += typ
	return content, extraPackages, rawMessageTypes, typ
}

func (p Properties) String() string {
	result := ""
	for _, i := range p.SortedPropertyNames {
		result += "Property '" + i + "' =\n" + utils.Indent(p.Properties[i].String(), "  ")
	}
	return result
}

func (p *Properties) postPopulate(apiDef *APIDefinition) {
	// now all data should be loaded, let's sort the p.Properties
	if p.Properties != nil {
		p.SortedPropertyNames = make([]string, 0, len(p.Properties))
		for propertyName := range p.Properties {
			p.SortedPropertyNames = append(p.SortedPropertyNames, propertyName)
		}
		sort.Strings(p.SortedPropertyNames)
		members := make(map[string]bool, len(p.SortedPropertyNames))
		for _, j := range p.SortedPropertyNames {
			p.Properties[j].TypeName = utils.NormaliseLower(j, members)
			// subschemas also need to be triggered to postPopulate...
			p.Properties[j].postPopulate(apiDef)
		}
	}
}

func (p *Properties) UnmarshalJSON(bytes []byte) (err error) {
	errX := json.Unmarshal(bytes, &p.Properties)
	return errX
}

func (aP *AdditionalProperties) UnmarshalJSON(bytes []byte) (err error) {
	b, p := new(bool), new(JsonSubSchema)
	if err = json.Unmarshal(bytes, b); err == nil {
		aP.Boolean = b
		return
	}
	if err = json.Unmarshal(bytes, p); err == nil {
		aP.Properties = p
	}
	return
}

func (aP AdditionalProperties) String() string {
	if aP.Boolean != nil {
		return strconv.FormatBool(*aP.Boolean)
	}
	return aP.Properties.String()
}

func (items Items) String() string {
	result := ""
	for i, j := range items {
		result += fmt.Sprintf("Item '%v' =\n", i) + utils.Indent(j.String(), "  ")
	}
	return result
}

func (items Items) postPopulate(apiDef *APIDefinition) {
	for i := range items {
		items[i].postPopulate(apiDef)
	}
}

func describeList(name string, value interface{}) string {
	if reflect.ValueOf(value).IsValid() {
		if !reflect.ValueOf(value).IsNil() {
			return fmt.Sprintf("%v\n", name) + utils.Indent(fmt.Sprintf("%v", reflect.Indirect(reflect.ValueOf(value)).Interface()), "  ")
		}
	}
	return ""
}

// If item is not null, then return a description of it. If it is a pointer, dereference it first.
func describe(name string, value interface{}) string {
	if reflect.ValueOf(value).IsValid() {
		if !reflect.ValueOf(value).IsNil() {
			return fmt.Sprintf("%-22v = '%v'\n", name, reflect.Indirect(reflect.ValueOf(value)).Interface())
		}
	}
	return ""
}

type CanPopulate interface {
	postPopulate(*APIDefinition)
}

func postPopulateIfNotNil(canPopulate CanPopulate, apiDef *APIDefinition) {
	if reflect.ValueOf(canPopulate).IsValid() {
		if !reflect.ValueOf(canPopulate).IsNil() {
			canPopulate.postPopulate(apiDef)
		}
	}
}

func (subSchema *JsonSubSchema) postPopulate(apiDef *APIDefinition) {
	if subSchema.TypeName == "" {
		members := make(map[string]bool, 1)
		switch {
		case subSchema.Title != nil && *subSchema.Title != "" && len(*subSchema.Title) < 40:
			subSchema.TypeName = utils.NormaliseLower(*subSchema.Title, members)
		case subSchema.Description != nil && *subSchema.Description != "" && len(*subSchema.Description) < 40:
			subSchema.TypeName = utils.NormaliseLower(*subSchema.Description, members)
		case subSchema.RefSubSchema != nil && subSchema.RefSubSchema.TypeName != "":
			subSchema.TypeName = subSchema.RefSubSchema.TypeName
		default:
			subSchema.TypeName = "X"
		}
	}
	// Arrays should get their name from their parent subschema. Note we set
	// this before calling postPopulate on subSchema.Items, to make sure we get
	// there first! If already set, it won't get updated later.
	if subSchema.Items != nil {
		subSchema.Items.TypeName = subSchema.TypeName
	}
	postPopulateIfNotNil(subSchema.AllOf, apiDef)
	postPopulateIfNotNil(subSchema.AnyOf, apiDef)
	postPopulateIfNotNil(subSchema.OneOf, apiDef)
	postPopulateIfNotNil(subSchema.Items, apiDef)
	postPopulateIfNotNil(subSchema.Properties, apiDef)
	// If we have a $ref pointing to another schema, keep a reference so we can
	// discover TypeName later when we generate the type definition
	subSchema.RefSubSchema = apiDef.cacheJsonSchema(subSchema.Ref)
}
