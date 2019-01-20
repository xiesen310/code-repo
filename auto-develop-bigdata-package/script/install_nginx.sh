#!/bin/bash

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# rpm 安装nginx
rpm -ivh /zork/src/nginx-1.12.1-1.el7.ngx.x86_64.rpm

# 复制icube的前端页面
tar -zxvf /zork/src/zorkdata.tar.gz -C  /zork/zkce

# 获取当前主机ip
IP=`ip addr | grep 'state UP' -A2 | tail -n1 | awk '{print $2}' | awk -F"/" '{print $1}'`

source ./globals.env

# 修改配置文件
mv /etc/nginx/conf.d/default.conf default.conf.bak
touch /etc/nginx/conf.d/default.conf
cat >> /etc/nginx/conf.d/default.conf << EOF
server {
        listen       80; #监听端口
        server_name  $IP; #当前主机ip地址

        location = / {
                root /zork/zkce/zorkdata; #界面所在目录
        }

        location /webserver/ {
                proxy_pass $ICUBE_URL; #webserver地址
        }
		
		location /webserver/websocket/ {
                proxy_pass $ICUBE_URL; #websocket地址，同webserver地址
        }
		
        location /grafana/ {
                proxy_pass $GRAFANA_URL/;#grafana地址
        }
}
EOF

# 启动nginx
systemctl start nginx

sleep 5s
green_echo ()    { [ "$HASTTY" == 0 ] && echo "$@" || echo -e "\033[032;1m$@\033[0m"; }

green_echo "= = = = = = = = = = = = = = = = = = = = = = = = = = == = ="
# 查看安装
systemctl status nginx
#nginx -v
