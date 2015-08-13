#!/usr/bin/env bash

set -e

#we prefer system-wide installations of gradle than gradlew (mainly to save the annoyance of downloading the gradle wrapper)
if hash gradle 2>/dev/null; then
    gradle build shadowJar
else
    ./gradlew build shadowJar --no-daemon
fi

rm build/libs/LegendArena.jar
mv build/libs/LegendArena-all.jar build/libs/LegendArena.jar