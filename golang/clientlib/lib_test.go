package clientlib

import (
	"testing"
)

func TestHello(t *testing.T) {
	msg := "hello"
	want := "clientlib hello"
	if msg != want {
		t.Errorf("got %v, want %s", msg, want)
	}
}
