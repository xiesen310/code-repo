#!/bin/bash

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

source ./globals.env
# 解压到/zork/zkce目录下
tar -zxvf /zork/src/tomcat-icube.tar.gz  -C $ZORK_BASE_PATH

PREKAFKA_PATH=/zork/zkce/tomcat-prekafka
# 解压prekafka到tomcat-prekafka下
tar -zxvf /zork/src/preKafka.tar.gz -C $PREKAFKA_PATH/webapps

# 修改prekafka配置文件
cat >> $PREKAFKA_PATH/webapps/preKafka/WEB-INF/classes/config.properties << EOF
kafka.servers = $KAFKA_SERVERS
zookeeper.url = $ZK_URL
logstruct.url = $ICUBE_URL/log/getLogColumns.do
metricstruct.url = $ICUBE_URL/metric/getMetricSetColumns.do

servicecode.url = $ICUBE_URL/externalapi/getProgram2SystemServiceCode.do
systemservicecode.url = $ICUBE_URL/externalapi/getHostname2SystemServiceCode.do
iphostname.url = $ICUBE_URL/externalapi/getIPHostname.do

EOF

# 启动prekafka
$PREKAFKA_PATH/bin/startup.sh

sleep 5s
source ./functions

green_echo "= = = = = = = = = = = = = = = = = = = = = = = = = = == = ="

# 查看安装
ps -ef | grep tomcat
