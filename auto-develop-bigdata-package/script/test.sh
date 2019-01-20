#!/bin/bash

source ./globals.env

echo "$es_user_password"
echo "$es_user_name"

useradd $es_user_name;echo "$es_user_password" | passwd \-\-stdin $es_user_name
