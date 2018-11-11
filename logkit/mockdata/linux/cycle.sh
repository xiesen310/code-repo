#!/bin/bash
command=$1
for i in {1..100}; do ./$command;sleep 1s;
done
