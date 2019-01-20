#!/bin/bash

source ./functions 
green_echo "安装kafka开始"
# 存在依赖关系，需要判断java和zookeeper是否存在

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压zookeeper到/zork/zkce目录下
green_echo "解压kafka压缩包开始..."
tar -xf /zork/src/kafka_2.11-1.1.1.tgz -C /zork/zkce
green_echo "解压kafka压缩包完成"

# 加载全局变量
source ./globals.env

# 定义kafka的安装目录
KAFKA_PATH=$ZORK_BASE_PATH/kafka

# 修改kafka目录
mv /zork/zkce/kafka_2.11-1.1.1  $KAFKA_PATH

# 配置kafka环境变量
green_echo "设置kafka环境变量"
cat >> /etc/profile << EOF
# setting Kafka environment
export KAFKA_HOME=$KAFKA_PATH
export PATH=\$PATH:\$KAFKA_HOME/bin
EOF

# 重新加载配置文件
source /etc/profile

# 获取当前系统的主机名
LOCAL_HOSTNAME=hostname -s 


# 修改配置文件
green_echo "修改kafka配置文件"
mv $KAFKA_PATH/config/server.properties $KAFKA_PATH/config/server.properties.bak
touch $KAFKA_PATH/config/server.properties
KAFKA_CONF=$KAFKA_PATH/config/server.properties
BROKER_ID=0
echo broker.id=$BROKER_ID >> $KAFKA_CONF
echo num.network.threads=3 >> $KAFKA_CONF
echo num.io.threads=8 >> $KAFKA_CONF
echo socket.send.buffer.bytes=102400 >> $KAFKA_CONF
echo socket.receive.buffer.bytes=102400 >> $KAFKA_CONF
echo socket.request.max.bytes=104857600 >> $KAFKA_CONF
echo log.dirs=$KAFKA_PATH/kafka-logs >> $KAFKA_CONF
echo num.partitions=1 >> $KAFKA_CONF
echo num.recovery.threads.per.data.dir=1 >> $KAFKA_CONF
echo offsets.topic.replication.factor=1 >> $KAFKA_CONF
echo transaction.state.log.replication.factor=1 >> $KAFKA_CONF
echo transaction.state.log.min.isr=1 >> $KAFKA_CONF
echo log.retention.hours=168 >> $KAFKA_CONF
echo log.segment.bytes=1073741824 >> $KAFKA_CONF
echo log.retention.check.interval.ms=300000 >> $KAFKA_CONF
echo zookeeper.connect=$LOCAL_HOSTNAME:2181 >> $KAFKA_CONF
echo zookeeper.connection.timeout.ms=6000 >> $KAFKA_CONF
echo group.initial.rebalance.delay.ms=0 >> $KAFKA_CONF


# 创建数据目录
mkdir $KAFKA_PATH/data
touch $KAFKA_PATH/data/myid
echo $BROKER_ID >> $KAFKA_PATH/data/myid

# 启动kafka
green_echo "后台启动kafka"
nohup kafka-server-start.sh $KAFKA_CONF & 
sleep 10s

# 查看kafka状态
green_kafka "查看kafka状态信息"
jps

green_echo "安装kafka完成"
