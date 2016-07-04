#!/bin/bash
#
# Build release package of the current project and deploy the artifacts to
# Open Source Project Repository Hosting (https://oss.sonatype.org/).
# Copyright (C) 2016 Chessplorer.org
#

MVN=mvn
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export LANG=en
set -e

cd $PROJECT_DIR
$MVN -Pchessplorer-release clean package deploy
