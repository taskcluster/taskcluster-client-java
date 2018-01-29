package model

import (
	"encoding/json"
	"fmt"
	"log"
	"net/url"
	"strconv"
	"strings"

	"github.com/taskcluster/jsonschema2go"
	"github.com/taskcluster/taskcluster-client-java/codegenerator/utils"
)

type JsonSubSchema jsonschema2go.JsonSubSchema

func (jsonSubSchema *JsonSubSchema) TypeDefinition(level int, extraPackages map[string]bool) (classDefinition, comment, typ string) {
	if d := jsonSubSchema.Description; d != nil {
		if desc := *d; desc != "" {
			comment += desc
		}
	}
	if comment != "" && comment[len(comment)-1:] != "\n" {
		comment += "\n"
	}

	// Create comments for metadata in a single paragraph. Only start new
	// paragraph if we discover after inspecting all possible metadata, that
	// something has been specified. If there is no metadata, no need to create
	// a new paragraph.
	var metadata string
	if enum := jsonSubSchema.Enum; enum != nil {
		metadata += "Possible values:\n"
		for _, i := range enum {
			switch i.(type) {
			case float64:
				metadata += fmt.Sprintf("    * %v\n", i)
			default:
				metadata += fmt.Sprintf("    * %q\n", i)
			}
		}
	}
	if def := jsonSubSchema.Default; def != nil {
		var value string
		switch (*def).(type) {
		case bool:
			value = strconv.FormatBool((*def).(bool))
		case float64:
			value = strconv.FormatFloat((*def).(float64), 'g', -1, 64)
		default:
			v, err := json.MarshalIndent(*def, "", "  ")
			if err != nil {
				panic(fmt.Sprintf("couldn't marshal %+v", *def))
			}
			value = string(v)
		}
		metadata += "Default:    " + value + "\n"
	}
	if regex := jsonSubSchema.Pattern; regex != nil {
		metadata += "Syntax:     " + *regex + "\n"
	}
	if minItems := jsonSubSchema.MinLength; minItems != nil {
		metadata += "Min length: " + strconv.Itoa(*minItems) + "\n"
	}
	if maxItems := jsonSubSchema.MaxLength; maxItems != nil {
		metadata += "Max length: " + strconv.Itoa(*maxItems) + "\n"
	}
	if minimum := jsonSubSchema.Minimum; minimum != nil {
		metadata += "Mininum:    " + strconv.Itoa(*minimum) + "\n"
	}
	if maximum := jsonSubSchema.Maximum; maximum != nil {
		metadata += "Maximum:    " + strconv.Itoa(*maximum) + "\n"
	}
	// Here we check if metadata was specified, and only create new
	// paragraph (`*\n`) if something was.
	if len(metadata) > 0 {
		comment += "\n" + metadata
	}

	if URL := jsonSubSchema.SourceURL; URL != "" {
		u, err := url.Parse(URL)
		if err == nil && u.Scheme != "file" {
			if len(comment) > 1 {
				comment += "\n"
			}
			comment += "See " + URL + "\n"
		}
	}
	comment = utils.Comment(comment, "")

	typ = "Object"
	if p := jsonSubSchema.Type; p != nil {
		typ = *p
	}
	if p := jsonSubSchema.RefSubSchema; p != nil {
		j := JsonSubSchema(*jsonSubSchema.RefSubSchema)
		_, _, possSimpleType := (&j).TypeDefinition(0, make(map[string]bool))
		switch possSimpleType {
		case "":
			typ = p.TypeName
		default:
			typ = possSimpleType
		}
	}
	switch typ {
	case "array":
		if jsonSubSchema.Items != nil {
			if jsonSubSchema.Items.Type != nil {
				j := JsonSubSchema(*jsonSubSchema.Items)
				subClassDefinition, _, subType := (&j).TypeDefinition(level, extraPackages)
				if subType == "" {
					log.Fatalf("%v returns an empty Type", jsonSubSchema.Items.SourceURL)
				}
				typ = subType + "[]"
				classDefinition = subClassDefinition

			} else {
				if refSubSchema := jsonSubSchema.Items.RefSubSchema; refSubSchema != nil {
					if refSubSchema.TypeName == "" {
						log.Fatalf("%v has not set TypeName", refSubSchema.SourceURL)
					}
					typ = refSubSchema.TypeName + "[]"
				}
			}
		} else {
			typ = "Object[]"
		}

	case "object":
		if s := jsonSubSchema.Properties; s != nil {
			typ = jsonSubSchema.TypeName
			classDefinition = "\n" + strings.Repeat("    ", level) + "public class " + typ + " {"
			for _, j := range s.SortedPropertyNames {
				// recursive call to build go types inside structs
				var subType string
				c := JsonSubSchema(*s.Properties[j])
				subMember := s.MemberNames[j]
				subClassDefinition, subComment, subType := (&c).TypeDefinition(level+1, extraPackages)
				classDefinition += "\n"
				classDefinition += subClassDefinition
				classDefinition += "\n"
				classDefinition += utils.Indent(subComment, strings.Repeat("    ", level+1), false)
				classDefinition += strings.Repeat("    ", level+1) + "public " + subType + " " + subMember + ";"
			}
			classDefinition += "\n"
			classDefinition += strings.Repeat("    ", level) + "}"
			classDefinition += "\n"
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
	}
	return classDefinition, comment, typ
}
