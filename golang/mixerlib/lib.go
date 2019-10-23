package mixerlib

import (
	"fmt"
)

// Mixer should handle mixing the coins and depositing to the input addresses
func Mixer(addresses []string) bool {
	fmt.Printf("Mix into these addresses=%s\n", addresses)
	return true
}
