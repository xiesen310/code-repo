#!/bin/bash

# 创建文件夹
if [ ! -d "/zork/zkce" ]; then
   mkdir /zork/zkce
fi

# 解压jstorm到/zork/zkce目录下
unzip  /zork/src/jstorm-2.1.1.zip -d /zork/zkce

mv /zork/zkce/jstorm-2.1.1 /zork/zkce/jstorm

# 定义jstorm安装目录
jstorm_path=/zork/zkce/jstorm

# 获取当前主机ip
IP=`ip addr | grep 'state UP' -A2 | tail -n1 | awk '{print $2}' | awk -F"/" '{print $1}'`

# 加载全局变量
source ./globals.env

# 配置jstorm环境变量
cat >> /root/.bashrc << EOF
# set jsorm
export JSTORM_HOME=$jstorm_path
export PATH=\$PATH:\$JSTORM_HOME/bin
EOF

# 重新加载配置文件
source /root/.bashrc

mv $jstorm_path/conf/storm.yaml $jstorm_path/conf/storm.yaml.bak
touch $jstorm_path/conf/storm.yaml

# 配置jsrom
cat >> $jstorm_path/conf/storm.yaml << EOF
 storm.zookeeper.servers:  
     - "$IP"  
  
 storm.zookeeper.root: "/jstorm"  
  
 nimbus.host: "$IP"  
 nimbus.host.start.supervisor: false  
   
# nimbus.childopts: "-Xmx256m"  
# supervisor.childopts: "-Xmx256m"  
# worker.childopts: "-Xmx128m"  
  
 storm.local.dir: "$jstorm_path/data"  
  
 supervisor.slots.ports:  
    - 6800  
    - 6801  
    - 6802  
    - 6803 
EOF

mkdir /root/.jstorm
cp $jstorm_path/conf/storm.yaml /root/.jstorm

# # # # # 设置jstorm ui # # # # #
tar -zxf /zork/src/jstorm-ui.tar.gz -C /zork/zkce
tar -zxf  /zork/src/jstorm-ui-2.1.1.tar.gz -C /zork/zkce/jstorm-ui/webapps
mv /zork/zkce/jstorm-ui/webapps/ROOT /zork/zkce/jstorm-ui/webapps/ROOT.old
ln -s /zork/zkce/jstorm-ui/webapps/jstorm-ui-2.1.1 /zork/zkce/jstorm-ui/webapps/jstorm-ui-2.1.1/ROOT

sleep 2s
# 启动jstorm
nohup jstorm nimbus &
nohup jstorm supervisor &

sleep 5s

green_echo ()    { [ "$HASTTY" == 0 ] && echo "$@" || echo -e "\033[032;1m$@\033[0m"; }

green_echo "= = = = = = = = = = = = = = = = = = = = = = = = = = == = ="

# 查看jstorm状态
jps
# 启动jstorm-ui
/zork/zkce/jstorm-ui/bin/startup.sh

