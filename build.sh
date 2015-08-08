#!/usr/bin/env bash

set -e

if hash gradle 2>/dev/null; then
    gradle build shadowJar
else
    ./gradlew build shadowJar --no-daemon
fi

rm build/libs/LegendArena.jar
mv build/libs/LegendArena-all.jar build/libs/LegendArena.jar

if env | grep -q ^SNAPCI= then
    gradle upload
fi
