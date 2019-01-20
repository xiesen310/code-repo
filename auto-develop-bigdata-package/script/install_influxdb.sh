#!/bin/bash

source ./functions 
green_echo "安装influxdb开始"
# yum 本地安装
yum localinstall -y /zork/src/influxdb-1.2.4.x86_64.rpm

# 启动influxdb
service influxdb start
sleep 5s

# 查看influxdb状态
green_echo "查看influxdb状态信息"
service influxdb status

sleep 1s
green_echo "安装influxdb完成"
