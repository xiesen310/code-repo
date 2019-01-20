#!/bin/bash

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# yum 安装
yum localinstall -y /zork/src/grafana-4.3.2-1.x86_64.rpm

# 更换js
rm -rf /usr/share/grafana/public/app/boot.d709d8d5.js
cp /zork/src/boot.d709d8d5.js usr/share/grafana/public/app/

# 获取主机名
host=`hostname -s`
# 修改配置文件
mv /etc/grafana/grafana.ini /etc/grafana/grafana.ini.bak
touch /etc/grafana/grafana.ini

cat >> /etc/grafana/grafana.ini  << EOF
[paths]
[server]
domain = $host
root_url = http://$host:/grafana
[database]
[session]
[dataproxy]
[analytics]
[security]
[snapshots]
[users]
default_theme = light
[auth]
[auth.anonymous]
[auth.github]
[auth.google]
[auth.generic_oauth]
[auth.grafana_com]
[auth.proxy]
[auth.basic]
[auth.ldap]
[smtp]
[emails]
[log]
[log.console]
[log.file]
[log.syslog]
[event_publisher]
[dashboards.json]
[alerting]
[metrics]
[metrics.graphite]
[grafana_com]
[external_image_storage]
[external_image_storage.s3]
[external_image_storage.webdav]
EOF

# 启动服务
service grafana-server start

source ./functions
green_echo "= = = = = = = = = = = = = = = = = = = = = = = = = = == = ="
# 查看安装
ps -ef | grep grafana
