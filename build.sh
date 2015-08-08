#!/usr/bin/env bash

if hash gradle 2>/dev/null; then
    gradle build shadowJar
else
    ./gradlew build shadowJar --no-daemon
fi

#gradle build shadowJar || { ./gradlew build shadowJar }
rm build/libs/`basename \`pwd\``.jar
mv build/libs/LegendArena-all.jar build/libs/LegendArena.jar
