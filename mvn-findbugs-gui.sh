#!/bin/bash
#
# Analyze source code with Find Bugs and show results in a GUI window.
# Copyright (C) 2016 Chessplorer.org
#

MVN=mvn
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export LANG=en
set -e

cd $PROJECT_DIR
$MVN findbugs:check findbugs:gui
