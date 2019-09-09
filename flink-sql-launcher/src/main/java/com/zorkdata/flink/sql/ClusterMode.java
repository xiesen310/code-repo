package com.zorkdata.flink.sql;

public enum ClusterMode {

    local(0),standalone(1),yarn(2),yarnPer(3);

    private int type;

    ClusterMode(int type){
        this.type = type;
    }
}
