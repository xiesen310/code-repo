#!/bin/bash

source ./functions 
green_echo "安装zookeeper开始"

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压zookeeper到/zork/zkce目录下
green_echo "解压zookeeper安装包开始..."
tar -zxvf /zork/src/zookeeper-3.4.8.tar.gz -C /zork/zkce
green_echo "解压zookeeper安装包完成"

# 加载全局变量
source ./globals.env

# 定义zookeeper的安装目录
ZK_HOME=$ZORK_BASE_PATH/zookeeper

# 修改zookeeper目录
mv /zork/zkce/zookeeper-3.4.8 $ZK_HOME

# 配置zookeeper环境变量
green_echo "添加zookeeper环境变量"
cat >> /etc/profile << EOF
# setting Zookeeper environment
export ZOOKEEPER=$ZK_HOME
export PATH=\$PATH:\$ZOOKEEPER/bin
EOF

# 重新加载配置文件
source /etc/profile

# 获取当前系统的主机名
LOCAL_HOSTNAME=hostname -s 

# 修改配置文件
green_echo "修改zookeeper配置文件"
touch /zork/zkce/zookeeper/conf/zoo.cfg
echo tickTime=2000 >> /zork/zkce/zookeeper/conf/zoo.cfg
echo initLimit=10 >> /zork/zkce/zookeeper/conf/zoo.cfg
echo syncLimit=5 >> /zork/zkce/zookeeper/conf/zoo.cfg
echo dataDir=$ZK_HOME/data >> /zork/zkce/zookeeper/conf/zoo.cfg
echo dataLogDir=$ZK_HOME/log >> /zork/zkce/zookeeper/conf/zoo.cfg
echo clientPort=2181 >> /zork/zkce/zookeeper/conf/zoo.cfg
echo server.1=$LOCAL_HOSTNAME:2888:3888 >> /zork/zkce/zookeeper/conf/zoo.cfg


# 创建数据目录
mkdir $ZK_HOME/data
touch $ZK_HOME/data/myid
echo 1 >> $ZK_HOME/data/myid

# 启动zookeeper
green_echo "启动zookeeper服务"
zkServer.sh start
sleep 10s


green_echo "查看zookeeper状态"
# 查看zookeeper状态
zkServer.sh status

green_echo "安装zookeeper完成"
