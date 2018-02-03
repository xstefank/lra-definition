#!/usr/bin/env bash

[ $NARAYANA_VERSION ] || NARAYANA_VERSION=5.7.2.Final

if [ ! -f lra-coordinator-swarm.jar ]; then
    echo Downloading narayana-full-$NARAYANA_VERSION
    wget -nc http://www.jboss.org/jbosstm/downloads/$NARAYANA_VERSION/binary/narayana-full-$NARAYANA_VERSION-bin.zip
    unzip narayana-full-$NARAYANA_VERSION-bin.zip
    mv narayana-full-$NARAYANA_VERSION/rts/lra/lra-coordinator-swarm.jar ./
    rm -rf narayana-full-$NARAYANA_VERSION
    rm narayana-full-$NARAYANA_VERSION-bin.zip
fi

