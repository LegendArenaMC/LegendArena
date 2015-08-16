#!/usr/bin/env bash

echo "Building `basename \`pwd\``..."

set -e

#prefer system-wide installations of gradle than the gradle wrapper
#(mainly to save the annoyance of downloading the gradle runtime if we don't need to)
if hash gradle 2>/dev/null; then
    gradle build
else
    ./gradlew build --no-daemon
fi

#don't rebuild, quiet output and don't download new dependency jars
if [ -f scripts/.requireshadow ]; then
    echo "Shadowing libraries into jar... (this may take a moment - no output will be produced except for errors and warnings from :compileJava)"
    if hash gradle 2>/dev/null; then
        gradle shadowJar --quiet --no-rebuild --offline
    else
        ./gradlew shadowJar --no-daemon --quiet --no-rebuild --offline
    fi
    echo "Shadowing successful, renaming shadowed jar..."
    rm build/libs/`basename \`pwd\``.jar
    mv build/libs/`basename \`pwd\``-all.jar build/libs/`basename \`pwd\``.jar
fi

echo "Done!"