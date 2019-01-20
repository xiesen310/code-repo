#!/bin/bash

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压到/zork/zkce目录下
tar -xvf /zork/src/node-v8.12.0-linux-x64.tar.xz -C /zork/zkce

# 修改jdk目录
mv /zork/zkce/node-v8.12.0-linux-x64 /zork/zkce/nodejs

# 设置软连接
ln -s /zork/zkce/nodejs/bin/npm /usr/local/bin/
ln -s /zork/zkce/nodejs/bin/node /usr/local/bin/

# 配置环境变量
cat >> /etc/profile << EOF
# setting nodejs environment
export NODE_JS=/zork/zkce/nodejs
export PATH=\$PATH:\$NODE_JS/bin
EOF

# 重新加载文件
source /etc/profile

green_echo ()    { [ "$HASTTY" == 0 ] && echo "$@" || echo -e "\033[032;1m$@\033[0m"; }

green_echo "= = = = = = = = = = = = = = = = = = = = = = = = = = == = ="
# 查看安装
node -v
