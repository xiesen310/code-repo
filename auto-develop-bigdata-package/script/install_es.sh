#!/bin/bash

source ./functions
green_echo "安装elasticsearch开始..."

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压到/zork/zkce目录下
echo "解压elasticsearch文件开始..."
tar -zxf /zork/src/elasticsearch-5.1.2.tar.gz -C /zork/zkce
echo "解压elasticsearch文件完成"

# 定义es的安装目录
ES_PATH=/zork/zkce/elasticsearch

# 修改elasticsearch目录
mv /zork/zkce/elasticsearch-5.1.2 $ES_PATH

# 加载变量文件
source ./globals.env

# 添加es用户
green_echo "添加elasticsearch用户"
useradd $es_user_name;echo "$es_user_password" | passwd \-\-stdin $es_user_name

# 更新环境变量
source /etc/profile

# 加载自定义函数
source ./functions
green_echo "= = = = = = 查看jdk版本 = = = = = = "
java -version

# 修改配置文件
mv /zork/zkce/elasticsearch/config/elasticsearch.yml /zork/zkce/elasticsearch/config/elasticsearch.yml.bak
touch /zork/zkce/elasticsearch/config/elasticsearch.yml
cat >> /zork/zkce/elasticsearch/config/elasticsearch.yml << EOF
cluster.name: $es_cluster_name
node.name: $es_node_name
path.data: $ES_PATH/data
path.logs: $ES_PATH/logs
network.host: $es_network_host
http.port: $es_http_port
EOF

# 为普通用户添加elasticsearch权限
chmod 777 -R /zork/zkce/elasticsearch

# 处理es安装异常信息
# max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
cat >> /etc/security/limits.conf << EOF
# Custom information

* soft nofile 65536
* hard nofile 131072
* soft nproc 2048
* hard nproc 4096
EOF

# max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
cat >> /etc/sysctl.conf  << EOF
# Custom information

vm.max_map_count=655360
EOF
sysctl -p
green_echo "安装elasticsearch完成"
