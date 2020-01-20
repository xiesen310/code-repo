#!/usr/bin/env bash

export basepath=$(cd `dirname $0`; pwd)
source $basepath/utils.fc
FLINK_TASK_CONF=flink-kafka-connector.properties
parallelism=1
main_class=top.xiesen.kafka.Main
version=0.0.1
projectName=flink-kafka-connector

$FLINK_HOME/bin/flink run -c $main_class -d -p $parallelism $CTRL_DIR/../lib/$projectName-*.jar --configPath $CTRL_DIR/../conf/$FLINK_TASK_CONF > $CTRL_DIR/../logs/$projectName-$version.log &