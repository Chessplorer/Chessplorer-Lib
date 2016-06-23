#!/bin/bash
#
# Build release package of the current project.
# Copyright (C) 2016 Chessplorer.org
#

MVN=mvn
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export LANG=en
set -e

cd $PROJECT_DIR
$MVN -Prelease clean package
