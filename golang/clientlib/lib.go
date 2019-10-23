package clientlib

import (
	"fmt"
	"net/http"

	"github.com/gemini/jobcoin"
)

// HTTPClient should handle the interaction with the jobcoin rest api
func HTTPClient() (string, error) {
	fmt.Println(jobcoin.TransactionEndpoint)
	// TODO: Get/Post action
	// Consult the https://jobcoin.gemini.com/<instance>/api spec
	// for a description of payloads, error codes, etc.
	response, err := http.Get(jobcoin.TransactionEndpoint)

	defer response.Body.Close()

	return response.Status, err
}
