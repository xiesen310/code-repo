#!/bin/bash

source ./functions
green_echo "安装logstash开始..."

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压到/zork/zkce目录下
green_echo "解压logstash安装包..."
tar -zxf /zork/src/logstash-5.1.2.tar.gz -C /zork/zkce
green_echo "解压logstash完成"

# 定义logstash安装路径
logstash_path=/zork/zkce/logstash

# 修改filebeat目录
mv /zork/zkce/logstash-5.1.2 $logstash_path

# 加载全局变量
source ./globals.env 
# 修改logstash配置文件
green_echo "修改logstash配置文件"
mv $logstash_path/config/logstash.yml $logstash_path/config/logstash.yml.bak
touch $logstash_path/config/logstash.yml
cat >> $logstash_path/config/logstash.yml  << EOF
 node.name: $logstash_node_name
 http.host: "$logstasg_http_host"
EOF

sleep 2s
green_echo "安装logstash完成"
