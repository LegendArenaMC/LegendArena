#!/usr/bin/env bash

# This should only be used by CI services. In order to successfully make a correctly named jar (with a build number/commit hash), you probably want to edit the "mv" part of the build command.
# (that is, if you aren't using SnapCI - if you are using SnapCI you should be all good)

mvn package | tee build/build.log && mv target/*.jar build/LegendArena-Build-$REVISION.jar