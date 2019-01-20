#!/bin/bash

# 加载自定义函数
source ./functions
green_echo "开始安装filebeat"

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压到/zork/zkce目录下
green_echo "正在解压filebeat安装包..."
tar -zxf /zork/src/filebeat-5.1.2-linux-x86_64.tar.gz -C /zork/zkce
green_echo "解压filebeat安装包完成"

# 修改filebeat目录
mv /zork/zkce/filebeat-5.1.2-linux-x86_64 /zork/zkce/filebeat

green_echo "= = = = = = = = = = = = = = = = = = = = = = = = = = == = ="
# 列举安装目录
tree /zork/zkce/filebeat 

green_echo "安装filebeat完成"
