package model

import "fmt"

////////////////////////////////////////////////////////////////////////
//
// From: http://schemas.taskcluster.net/base/v1/exchanges-reference.json
//
////////////////////////////////////////////////////////////////////////

type Exchange struct {
	Version        interface{}     `json:"version"`
	Title          string          `json:"title"`
	Description    string          `json:"description"`
	ExchangePrefix string          `json:"exchangePrefix"`
	Entries        []ExchangeEntry `json:"entries"`

	apiDef *APIDefinition
}

func (exchange *Exchange) String() string {
	var result string = fmt.Sprintf(
		"Version         = '%v'\n"+
			"Title           = '%v'\n"+
			"Description     = '%v'\n"+
			"Exchange Prefix = '%v'\n",
		exchange.Version, exchange.Title, exchange.Description,
		exchange.ExchangePrefix)
	for i, entry := range exchange.Entries {
		result += fmt.Sprintf("Entry %-6v= \n%v", i, entry.String())
	}
	return result
}

func (exchange *Exchange) postPopulate(apiDef *APIDefinition) {
	for i := range exchange.Entries {
		exchange.Entries[i].Parent = exchange
		exchange.Entries[i].postPopulate(apiDef)
	}
}

func (exchange *Exchange) setAPIDefinition(apiDef *APIDefinition) {
	exchange.apiDef = apiDef
}

type ExchangeEntry struct {
	Type        string         `json:"type"`
	Exchange    string         `json:"exchange"`
	Name        string         `json:"name"`
	Title       string         `json:"title"`
	Description string         `json:"description"`
	RoutingKey  []RouteElement `json:"routingKey"`
	Schema      string         `json:"schema"`

	Parent  *Exchange
	Payload *JsonSubSchema
}

func (entry *ExchangeEntry) postPopulate(apiDef *APIDefinition) {
	entry.Parent.apiDef.schemaURLs = append(entry.Parent.apiDef.schemaURLs, entry.Schema)
}

func (entry *ExchangeEntry) String() string {
	var result string = fmt.Sprintf(
		"    Entry Type        = '%v'\n"+
			"    Entry Exchange    = '%v'\n"+
			"    Entry Name        = '%v'\n"+
			"    Entry Title       = '%v'\n"+
			"    Entry Description = '%v'\n",
		entry.Type, entry.Exchange, entry.Name, entry.Title,
		entry.Description)
	for i, element := range entry.RoutingKey {
		result += fmt.Sprintf("    Routing Key Element %-6v= \n%v", i, element.String())
	}
	result += fmt.Sprintf("    Entry Schema      = '%v'\n", entry.Schema)
	return result
}

type RouteElement struct {
	Name          string `json:"name"`
	Summary       string `json:"summary"`
	Constant      string `json:"constant"`
	MultipleWords bool   `json:"multipleWords"`
	Required      bool   `json:"required"`
}

func (re *RouteElement) String() string {
	return fmt.Sprintf(
		"        Element Name      = '%v'\n"+
			"        Element Summary   = '%v'\n"+
			"        Element Constant  = '%v'\n"+
			"        Element M Words   = '%v'\n"+
			"        Element Required  = '%v'\n",
		re.Name, re.Summary, re.Constant, re.MultipleWords, re.Required)
}

func (exchange *Exchange) generateAPICode(exchangeName string) string {
	return ""
}

func (entry *ExchangeEntry) generateAPICode(exchangeEntry string) string {
	return ""
}
