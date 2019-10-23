package mixerlib

import (
	"testing"
)

func TestHello(t *testing.T) {
	msg := "hello"
	want := "mixerlib hello"
	if msg != want {
		t.Errorf("got %v, want %s", msg, want)
	}
}
