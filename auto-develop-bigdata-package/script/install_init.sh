#!/bin/bash

# 关闭防火墙
systemctl stop firewalld.service
systemctl disable firewalld.service

# 安装本地依赖
yum install -y tree

