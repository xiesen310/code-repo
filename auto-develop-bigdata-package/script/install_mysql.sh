#!/bin/bash

# 加载自定义函数
source ./functions
green_echo "安装mysql开始"
# 卸载系统自带的mariadb
rpm -e --nodeps `rpm -qa | grep mariadb`

# 解压到/zork/zkce目录下
green_echo "解压mysql安装包..."
tar -xvf /zork/src/mysql-5.7.23-1.el7.x86_64.rpm-bundle.tar -C /zork/zkce
green_echo "解压mysql安装包完成"

rpm -ivh /zork/zkce/mysql-community-common-5.7.23-1.el7.x86_64.rpm
rpm -ivh /zork/zkce/mysql-community-libs-5.7.23-1.el7.x86_64.rpm
rpm -ivh /zork/zkce/mysql-community-client-5.7.23-1.el7.x86_64.rpm
rpm -ivh /zork/zkce/mysql-community-server-5.7.23-1.el7.x86_64.rpm

# 删除安装包
rm -rf /zork/zkce/mysql-community-*.rpm

# 启动mysql
green_echo "启动mysql数据库..."
systemctl start mysqld.service

# 设置mysql开机启动
green_echo "设置mysql开机自启"
systemctl enable mysqld

source ./functions
source ./globals.env

# 查看原始密码
red_echo "查看原始密码如下:"
grep 'password'  /var/log/mysqld.log
red_echo "请输入原始密码，然后执行如下命令:"
green_echo "set password=password('$MYSQL_PASSWORD');"
green_echo "exit;"
mysql -uroot -p

# 修改权限
mysql -uroot -p$MYSQL_PASSWORD -e "grant all privileges on *.* to 'root'@'%' identified by '$MYSQL_PASSWORD';flush privileges;"

green_echo "查看mysql启动进程"
# 查看进程
systemctl status mysqld.service

green_echo "安装mysql完成"
