#!/bin/bash -xveu

# call this script with -n to skip code generation

GENERATE=true
while getopts ":n" opt; do
    case "${opt}" in
        n)  GENERATE=false
            ;;  
    esac
done

cd "$(dirname "${0}")"
rm -rf src/main/java/org/mozilla/taskcluster/client/*/*.java

rm -rf "${GOPATH}/bin/generatemodel"
rm -rf "${GOPATH}"/pkg/*/github.com/*/taskcluster-client-java
go clean -i -x ./...

go get ./...
go install -v -x ./codegenerator/generatemodel
"${GENERATE}" && go generate -v ./...
go install -v -x ./...
go fmt ./...
go get golang.org/x/tools/cmd/vet
go vet -x ./...
go test -v ./...

# finally check that generated files have been committed, and that
# formatting code resulted in no changes...
git status
[ $(git status --porcelain | wc -l) == 0 ]

mvn clean install
