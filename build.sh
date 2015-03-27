#!/usr/bin/env bash

# This should only be used by CI services. In order to successfully make a correctly named jar, you probably want to edit the "mv" command
# (that is, if you aren't using CircleCI - if you are using CircleCI you should be all good)

mkdir build
mvn package | tee build/build.log && mv target/*.jar build/LegendArena-$CIRCLE_BUILD_NUM-$CIRCLE_SHA1.jar