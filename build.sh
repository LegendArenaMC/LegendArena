#!/usr/bin/env bash
if which tput >/dev/null 2>&1; then
  ncolors=$(tput colors)
fi
if [ -t 1 ] && [ -n "$ncolors" ] && [ "$ncolors" -ge 8 ]; then
  RED="$(tput setaf 1)"
  GREEN="$(tput setaf 2)"
  YELLOW="$(tput setaf 3)"
  BLUE="$(tput setaf 4)"
  MAGENTA="$(tput setaf 5)"
  CYAN="$(tput setaf 6)"
  GRAY="$(tput setaf 7)"
  BOLD="$(tput bold)"
  NORMAL="$(tput sgr0)"
else
  RED=""
  GREEN=""
  YELLOW=""
  BLUE=""
  MAGENTA=""
  CYAN=""
  GRAY=""
  BOLD=""
  NORMAL=""
fi

BUILDDIR=`pwd`

echo "${BOLD}${BLUE}+ ${GREEN}Building ${CYAN}`basename $BUILDDIR`${GREEN} on branch ${CYAN}`git rev-parse --abbrev-ref HEAD`${GREEN}..."

# Gradle builder

echo "Building `basename \`pwd\`` on branch `git rev-parse --abbrev-ref HEAD`..."

if [ -f "$BUILDDIR/.buildconfig" ]; then
  gradle `cat $BUILDDIR/.buildconfig`
else
  gradle build
fi

echo "Done!"
