#!/bin/bash

# 加载自定义函数
source ./functions

# 卸载本机安装的openjdk
green_echo "卸载openjdk"
rpm -e --nodeps `rpm -qa | grep openjdk`

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压到/zork/zkce目录下
green_echo "解压jdk安装包..."
tar -zxvf /zork/src/jdk-8u161-linux-x64.tar.gz -C /zork/zkce
green_echo "解压jdk安装包完成"

# 修改jdk目录
mv /zork/zkce/jdk1.8.0_161 /zork/zkce/java

# 配置环境变量
green_echo "修改环境变量"
cat >> /etc/profile << EOF
# setting Java environment
export JAVA_HOME=/zork/zkce/java/
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

# 重新加载文件
source /etc/profile

green_echo "检查安装"
# 查看安装
java -version
