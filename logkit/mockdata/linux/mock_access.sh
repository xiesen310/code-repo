#!/bin/bash

function current_timestamp(){
current=`date "+%Y-%m-%d %H:%M:%S"`
timeStamp=`date -d "$current" +%s`
#将current转换为时间戳，精确到毫秒
currentTimeStamp=$((timeStamp*1000+`date "+%N"`/1000000))
echo $currentTimeStamp
}

appids=(1000 1001 1002 1003)
appid_length=${#appids[@]}
appid_index=$[RANDOM%$appid_length]

ips=(192.168.1.91 192.168.1.92 192.168.1.93)
ip_length=${#ips[@]}
ip_index=$[RANDOM%$ip_length]

mid=`cat /proc/sys/kernel/random/uuid`
userid=$[RANDOM%30+20200]
login_types=(0 1)
login_type_length=${#login_types[@]}
login_type_index=$[RANDOM%$login_type_length]

request="GET /userList HTTP/1.1"
statuses=(200 404 408 500 504)
status_length=${#statuses[@]}
status_index=$[RANDOM%$status_length]

http_referer="https://www.baidu.com"

user_agent=("Mozilla/5.0 (Windows NT 6.1; WOW64)" "AppleWebKit/537.36 (KHTML, like Gecko)" "Chrome/47.0.2526.106 Safari/537.36")
user_agent_length=${#user_agent[@]}
user_agent__index=$[RANDOM%$user_agent_length]

time=$(current_timestamp)

echo -e ${appids[$appid_index]}'\t'${ips[$ip_index]}'\t'${mid}'\t'${userid}'\t'${login_types[$login_type_index]}'\t'${request}'\t'${statuses[$status_index]}'\t'${http_referer}'\t'${user_agent[$user_agent__index]}'\t'${time} >> b.log
