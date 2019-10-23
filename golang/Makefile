# Go parameters
GOCMD=go
GOBUILD=$(GOCMD) build
GOCLEAN=$(GOCMD) clean
GOTEST=$(GOCMD) test
GOGET=$(GOCMD) get
BINARY_NAME=mixer

all: clean deps build
build: deps
		$(GOBUILD) -o bin/$(BINARY_NAME) -v cmd/mixer-cli/main.go
test:
		$(GOTEST) -v ./...
clean:
		$(GOCLEAN)
		rm -f $(BINARY_NAME)
deps:
		$(GOGET) -u github.com/google/uuid

.PHONY: all build test clean deps
