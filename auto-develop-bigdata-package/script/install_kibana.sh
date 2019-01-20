#!/bin/bash
source ./functions
green_echo "安装kibana开始..."

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压到/zork/zkce目录下
echo "解压kibana文件开始..."
tar -zxf /zork/src/kibana-5.1.2-linux-x86_64.tar.gz -C /zork/zkce
echo "解压kibana文件完成"

# 定义es的安装目录
KIBANA_PATH=/zork/zkce/kibana

# 修改elasticsearch目录
mv /zork/zkce/kibana-5.1.2-linux-x86_64  $KIBANA_PATH

# 加载变量文件
source ./globals.env

# 修改配置文件
mv $KIBANA_PATH/config/kibana.yml $KIBANA_PATH/config/kibana.yml.bak
touch $KIBANA_PATH/config/kibana.yml
cat >> $KIBANA_PATH/config/kibana.yml << EOF
server.port: $kibana_server_port
server.host: "$kibana_server_host"
elasticsearch.url: "$kibana_elasticsearch_url"
kibana.index: ".kibana"
EOF

green_echo "安装kibana完成"
sleep 2s

# 启动
green_echo "kibana后台启动..."
nohup  $KIBANA_PATH/bin/kibana >> $KIBANA_PATH/nohub.out &
