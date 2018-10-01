#!/bin/bash -eu

GO_VERSION="$(go version 2>/dev/null | cut -f3 -d' ')"
GO_MAJ="$(echo "${GO_VERSION}" | cut -f1 -d'.')"
GO_MIN="$(echo "${GO_VERSION}" | cut -f2 -d'.')"
if [ -z "${GO_VERSION}" ]; then
  echo "Have you installed go? I get no result from \`go version\` command." >&2
  exit 64
elif [ "${GO_MAJ}" != "go1" ] || [ "${GO_MIN}" -lt 5 ]; then
  echo "Go version go1.x needed, where x >= 5, but the version I found is: '${GO_VERSION}'" >&2
  echo "I found it here:" >&2
  which go >&2
  echo "The complete output of \`go version\` command is:" >&2
  go version >&2
  exit 65
fi

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
git checkout -f src/main/java/org/mozilla/taskcluster/client/awsprovisioner/
git checkout -f src/main/java/org/mozilla/taskcluster/client/awsprovisionerevents/
git checkout -f src/main/java/org/mozilla/taskcluster/client/ec2manager/

rm -rf "${GOPATH}/bin/generatemodel"
rm -rf "${GOPATH}"/pkg/*/github.com/*/taskcluster-client-java
go clean -i ./...

go get ./...
go install -v ./codegenerator/generatemodel
"${GENERATE}" && go generate -v ./...
go install -v ./...
go fmt ./...
go vet ./...
go test -v ./...

# finally check that generated files have been committed, and that
# formatting code resulted in no changes...
git status
git diff | cat
[ $(git status --porcelain | wc -l) == 0 ]

mvn -e clean install
