package main

import (
	"flag"
	"fmt"
	"log"
	"os"
	"strings"

	"github.com/gemini/jobcoin/clientlib"
	"github.com/gemini/jobcoin/mixerlib"

	"github.com/google/uuid"
)

func inputDepositAddresses() []string {

	var svar string
	flag.StringVar(&svar, "addresses", "", "comma-separated list of new, unused Jobcoin addresses")

	flag.Parse()

	trimmed := strings.TrimSpace(svar)
	if trimmed == "" {
		instruction := `
Welcome to the Jobcoin mixer!
Please enter a comma-separated list of new, unused Jobcoin addresses
where your mixed Jobcoins will be sent. Example:

	./bin/mixer --addresses=bravo,tango,delta
`
		fmt.Println(instruction)
		os.Exit(-1)
	}

	return strings.Split(strings.ToLower(trimmed), ",")

}

func main() {
	addresses := inputDepositAddresses()
	depositAddress, err := uuid.NewUUID()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf(`
You may now send Jobcoins to address %s.

They will be mixed into %s and sent to your destination addresses.`, depositAddress, addresses)

	response, err := clientlib.HTTPClient()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(response)

	mixerlib.Mixer(addresses)
}
