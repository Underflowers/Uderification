#!/bin/bash

ROOT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd $ROOT_DIR
IMPLEMENTATION_DIR=./fruits-impl
DEPENDENCY_DIR=$IMPLEMENTATION_DIR/target/dependency

cd $IMPLEMENTATION_DIR
mvn package

cd $ROOT_DIR
mkdir -p $DEPENDENCY_DIR && (cd $DEPENDENCY_DIR; jar -xf ../*.jar)