#!/bin/bash
#
# Extract translation strings from source code.
# Copyright (C) 2016 Chessplorer.org
#

MVN=mvn
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export LANG=en
set -e

cd $PROJECT_DIR
$MVN -DskipTests=true com.googlecode.gettext-commons:gettext-maven-plugin:gettext
