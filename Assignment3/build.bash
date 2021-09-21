#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Brick.java
java Game
rm *.class