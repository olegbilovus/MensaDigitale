#!/bin/bash

cd $TRAVIS_BUILD_DIR
git pull origin HEAD:main
rm -rf docs/*
mv $TRAVIS_BUILD_DIR/target/site/jacoco/* ./docs
ls ./docs
TZ=Europe/Rome date >> ./docs/index.html
git add docs
git commit -am "travis-ci:updated coverage" || echo "no changes in docs"
git push origin HEAD:main
