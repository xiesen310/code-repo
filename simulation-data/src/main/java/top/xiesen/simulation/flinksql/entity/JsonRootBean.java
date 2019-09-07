package top.xiesen.simulation.flinksql.entity;

/**
 * @Description JsonRootBean
 * @Author 谢森
 * @Date 2019/8/8 18:35
 */
@SuppressWarnings("all")
public class JsonRootBean {
    private _id _id;
    private long timeStamp;
    private int ruleId;
    private int ruleType;
    private int linkLabelType;
    private int linkLabel;
    private int clientNetLabelType;
    private int clientIpPrefix;
    private long clientIp;
    private String clientPort;
    private String clientLocType;
    private String clientCountry;
    private String clientSubDiv1;
    private String clientSubDiv2;
    private String clientIpSiteId;
    private int serverNetLabelType;
    private int serverIpPrefix;
    private long serverIp;
    private String serverPort;
    private String serverLocType;
    private String serverCountry;
    private String serverSubDiv1;
    private String serverSubDiv2;
    private String serverIpSiteId;
    private int totalBytes;
    private int totalClientBytes;
    private int totalServerBytes;
    private int peakTotalBytes;
    private int valleyTotalBytes;
    private int totalPkts;
    private int totalClientPkts;
    private int totalServerPkts;
    private int avgPktLen;
    private int maxClientNum;
    private int maxConCurConnNum;
    private int newConnNum;
    private int dataPktNum;
    private int dataPktClientNum;
    private int dataPktServerNum;
    private int payloadByteNum;
    private int payloadByteClientNum;
    private int payloadByteServerNum;
    private int synNum;
    private int synClientNum;
    private int synServerNum;
    private int finNum;
    private int finClientNum;
    private int finServerNum;
    private int rstNum;
    private int rstClientNum;
    private int rstServerNum;
    private int successfulTcpConnNum;
    private int failedTcpConnNum;
    private int clientConnTime;
    private int serverConnTime;
    private int connTime;
    private int clientTerminateTime;
    private int serverTerminateTime;
    private int retxNum;
    private int retxClientNum;
    private int retxServerNum;
    private int retxSynNum;
    private int retxSynClientNum;
    private int retxSynServerNum;
    private int retxDataNum;
    private int retxDataClientNum;
    private int retxDataServerNum;
    private int retxFinNum;
    private int retxFinClientNum;
    private int retxFinServerNum;
    private int outOfOrderNum;
    private int outOfOrderClientNum;
    private int outOfOrderServerNum;
    private int zeroWindowNum;
    private int zeroWindowClientNum;
    private int zeroWindowServerNum;
    private int segLostNum;
    private int segLostClientNum;
    private int segLostServerNum;
    private int minClientWindow;
    private int minServerWindow;
    private int reqNum;
    private int respNum;
    private int reqTransferTime;
    private int respTransferTime;
    private int successfulTrans;
    private int quickTransResp;
    private int normalTransResp;
    private int slowTransResp;
    private int peakTransRespTime;
    private int transRespTime;
    private int retxDelayClientTime;
    private int retxDelayServerTime;
    private int dupAckNum;
    private int dupAckClientNum;
    private int dupAckServerNum;
    private int zeroWinWaitClientTime;
    private int zeroWinWaitServerTime;
    private int dataAckClientNum;
    private int dataAckServerNum;
    private int ackDelayClientTime;
    private int ackDelayServerTime;
    private int pingRequestNum;
    private int pingReplyNum;
    private int peakPingRespTime;
    private int avgPingRespTime;
    private int ruleServerIpSiteId;
    private long sessStartTime;
    private long sessDurationTime;
    private String clientIpStr;
    private String serverIpStr;
    private String probeIpStr;
    private int instId;
    private String metaType;

    public top.xiesen.simulation.flinksql.entity._id get_id() {
        return _id;
    }

    public void set_id(top.xiesen.simulation.flinksql.entity._id _id) {
        this._id = _id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getRuleType() {
        return ruleType;
    }

    public void setRuleType(int ruleType) {
        this.ruleType = ruleType;
    }

    public int getLinkLabelType() {
        return linkLabelType;
    }

    public void setLinkLabelType(int linkLabelType) {
        this.linkLabelType = linkLabelType;
    }

    public int getLinkLabel() {
        return linkLabel;
    }

    public void setLinkLabel(int linkLabel) {
        this.linkLabel = linkLabel;
    }

    public int getClientNetLabelType() {
        return clientNetLabelType;
    }

    public void setClientNetLabelType(int clientNetLabelType) {
        this.clientNetLabelType = clientNetLabelType;
    }

    public int getClientIpPrefix() {
        return clientIpPrefix;
    }

    public void setClientIpPrefix(int clientIpPrefix) {
        this.clientIpPrefix = clientIpPrefix;
    }

    public long getClientIp() {
        return clientIp;
    }

    public void setClientIp(long clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientLocType() {
        return clientLocType;
    }

    public void setClientLocType(String clientLocType) {
        this.clientLocType = clientLocType;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getClientSubDiv1() {
        return clientSubDiv1;
    }

    public void setClientSubDiv1(String clientSubDiv1) {
        this.clientSubDiv1 = clientSubDiv1;
    }

    public String getClientSubDiv2() {
        return clientSubDiv2;
    }

    public void setClientSubDiv2(String clientSubDiv2) {
        this.clientSubDiv2 = clientSubDiv2;
    }

    public String getClientIpSiteId() {
        return clientIpSiteId;
    }

    public void setClientIpSiteId(String clientIpSiteId) {
        this.clientIpSiteId = clientIpSiteId;
    }

    public int getServerNetLabelType() {
        return serverNetLabelType;
    }

    public void setServerNetLabelType(int serverNetLabelType) {
        this.serverNetLabelType = serverNetLabelType;
    }

    public int getServerIpPrefix() {
        return serverIpPrefix;
    }

    public void setServerIpPrefix(int serverIpPrefix) {
        this.serverIpPrefix = serverIpPrefix;
    }

    public long getServerIp() {
        return serverIp;
    }

    public void setServerIp(long serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerLocType() {
        return serverLocType;
    }

    public void setServerLocType(String serverLocType) {
        this.serverLocType = serverLocType;
    }

    public String getServerCountry() {
        return serverCountry;
    }

    public void setServerCountry(String serverCountry) {
        this.serverCountry = serverCountry;
    }

    public String getServerSubDiv1() {
        return serverSubDiv1;
    }

    public void setServerSubDiv1(String serverSubDiv1) {
        this.serverSubDiv1 = serverSubDiv1;
    }

    public String getServerSubDiv2() {
        return serverSubDiv2;
    }

    public void setServerSubDiv2(String serverSubDiv2) {
        this.serverSubDiv2 = serverSubDiv2;
    }

    public String getServerIpSiteId() {
        return serverIpSiteId;
    }

    public void setServerIpSiteId(String serverIpSiteId) {
        this.serverIpSiteId = serverIpSiteId;
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(int totalBytes) {
        this.totalBytes = totalBytes;
    }

    public int getTotalClientBytes() {
        return totalClientBytes;
    }

    public void setTotalClientBytes(int totalClientBytes) {
        this.totalClientBytes = totalClientBytes;
    }

    public int getTotalServerBytes() {
        return totalServerBytes;
    }

    public void setTotalServerBytes(int totalServerBytes) {
        this.totalServerBytes = totalServerBytes;
    }

    public int getPeakTotalBytes() {
        return peakTotalBytes;
    }

    public void setPeakTotalBytes(int peakTotalBytes) {
        this.peakTotalBytes = peakTotalBytes;
    }

    public int getValleyTotalBytes() {
        return valleyTotalBytes;
    }

    public void setValleyTotalBytes(int valleyTotalBytes) {
        this.valleyTotalBytes = valleyTotalBytes;
    }

    public int getTotalPkts() {
        return totalPkts;
    }

    public void setTotalPkts(int totalPkts) {
        this.totalPkts = totalPkts;
    }

    public int getTotalClientPkts() {
        return totalClientPkts;
    }

    public void setTotalClientPkts(int totalClientPkts) {
        this.totalClientPkts = totalClientPkts;
    }

    public int getTotalServerPkts() {
        return totalServerPkts;
    }

    public void setTotalServerPkts(int totalServerPkts) {
        this.totalServerPkts = totalServerPkts;
    }

    public int getAvgPktLen() {
        return avgPktLen;
    }

    public void setAvgPktLen(int avgPktLen) {
        this.avgPktLen = avgPktLen;
    }

    public int getMaxClientNum() {
        return maxClientNum;
    }

    public void setMaxClientNum(int maxClientNum) {
        this.maxClientNum = maxClientNum;
    }

    public int getMaxConCurConnNum() {
        return maxConCurConnNum;
    }

    public void setMaxConCurConnNum(int maxConCurConnNum) {
        this.maxConCurConnNum = maxConCurConnNum;
    }

    public int getNewConnNum() {
        return newConnNum;
    }

    public void setNewConnNum(int newConnNum) {
        this.newConnNum = newConnNum;
    }

    public int getDataPktNum() {
        return dataPktNum;
    }

    public void setDataPktNum(int dataPktNum) {
        this.dataPktNum = dataPktNum;
    }

    public int getDataPktClientNum() {
        return dataPktClientNum;
    }

    public void setDataPktClientNum(int dataPktClientNum) {
        this.dataPktClientNum = dataPktClientNum;
    }

    public int getDataPktServerNum() {
        return dataPktServerNum;
    }

    public void setDataPktServerNum(int dataPktServerNum) {
        this.dataPktServerNum = dataPktServerNum;
    }

    public int getPayloadByteNum() {
        return payloadByteNum;
    }

    public void setPayloadByteNum(int payloadByteNum) {
        this.payloadByteNum = payloadByteNum;
    }

    public int getPayloadByteClientNum() {
        return payloadByteClientNum;
    }

    public void setPayloadByteClientNum(int payloadByteClientNum) {
        this.payloadByteClientNum = payloadByteClientNum;
    }

    public int getPayloadByteServerNum() {
        return payloadByteServerNum;
    }

    public void setPayloadByteServerNum(int payloadByteServerNum) {
        this.payloadByteServerNum = payloadByteServerNum;
    }

    public int getSynNum() {
        return synNum;
    }

    public void setSynNum(int synNum) {
        this.synNum = synNum;
    }

    public int getSynClientNum() {
        return synClientNum;
    }

    public void setSynClientNum(int synClientNum) {
        this.synClientNum = synClientNum;
    }

    public int getSynServerNum() {
        return synServerNum;
    }

    public void setSynServerNum(int synServerNum) {
        this.synServerNum = synServerNum;
    }

    public int getFinNum() {
        return finNum;
    }

    public void setFinNum(int finNum) {
        this.finNum = finNum;
    }

    public int getFinClientNum() {
        return finClientNum;
    }

    public void setFinClientNum(int finClientNum) {
        this.finClientNum = finClientNum;
    }

    public int getFinServerNum() {
        return finServerNum;
    }

    public void setFinServerNum(int finServerNum) {
        this.finServerNum = finServerNum;
    }

    public int getRstNum() {
        return rstNum;
    }

    public void setRstNum(int rstNum) {
        this.rstNum = rstNum;
    }

    public int getRstClientNum() {
        return rstClientNum;
    }

    public void setRstClientNum(int rstClientNum) {
        this.rstClientNum = rstClientNum;
    }

    public int getRstServerNum() {
        return rstServerNum;
    }

    public void setRstServerNum(int rstServerNum) {
        this.rstServerNum = rstServerNum;
    }

    public int getSuccessfulTcpConnNum() {
        return successfulTcpConnNum;
    }

    public void setSuccessfulTcpConnNum(int successfulTcpConnNum) {
        this.successfulTcpConnNum = successfulTcpConnNum;
    }

    public int getFailedTcpConnNum() {
        return failedTcpConnNum;
    }

    public void setFailedTcpConnNum(int failedTcpConnNum) {
        this.failedTcpConnNum = failedTcpConnNum;
    }

    public int getClientConnTime() {
        return clientConnTime;
    }

    public void setClientConnTime(int clientConnTime) {
        this.clientConnTime = clientConnTime;
    }

    public int getServerConnTime() {
        return serverConnTime;
    }

    public void setServerConnTime(int serverConnTime) {
        this.serverConnTime = serverConnTime;
    }

    public int getConnTime() {
        return connTime;
    }

    public void setConnTime(int connTime) {
        this.connTime = connTime;
    }

    public int getClientTerminateTime() {
        return clientTerminateTime;
    }

    public void setClientTerminateTime(int clientTerminateTime) {
        this.clientTerminateTime = clientTerminateTime;
    }

    public int getServerTerminateTime() {
        return serverTerminateTime;
    }

    public void setServerTerminateTime(int serverTerminateTime) {
        this.serverTerminateTime = serverTerminateTime;
    }

    public int getRetxNum() {
        return retxNum;
    }

    public void setRetxNum(int retxNum) {
        this.retxNum = retxNum;
    }

    public int getRetxClientNum() {
        return retxClientNum;
    }

    public void setRetxClientNum(int retxClientNum) {
        this.retxClientNum = retxClientNum;
    }

    public int getRetxServerNum() {
        return retxServerNum;
    }

    public void setRetxServerNum(int retxServerNum) {
        this.retxServerNum = retxServerNum;
    }

    public int getRetxSynNum() {
        return retxSynNum;
    }

    public void setRetxSynNum(int retxSynNum) {
        this.retxSynNum = retxSynNum;
    }

    public int getRetxSynClientNum() {
        return retxSynClientNum;
    }

    public void setRetxSynClientNum(int retxSynClientNum) {
        this.retxSynClientNum = retxSynClientNum;
    }

    public int getRetxSynServerNum() {
        return retxSynServerNum;
    }

    public void setRetxSynServerNum(int retxSynServerNum) {
        this.retxSynServerNum = retxSynServerNum;
    }

    public int getRetxDataNum() {
        return retxDataNum;
    }

    public void setRetxDataNum(int retxDataNum) {
        this.retxDataNum = retxDataNum;
    }

    public int getRetxDataClientNum() {
        return retxDataClientNum;
    }

    public void setRetxDataClientNum(int retxDataClientNum) {
        this.retxDataClientNum = retxDataClientNum;
    }

    public int getRetxDataServerNum() {
        return retxDataServerNum;
    }

    public void setRetxDataServerNum(int retxDataServerNum) {
        this.retxDataServerNum = retxDataServerNum;
    }

    public int getRetxFinNum() {
        return retxFinNum;
    }

    public void setRetxFinNum(int retxFinNum) {
        this.retxFinNum = retxFinNum;
    }

    public int getRetxFinClientNum() {
        return retxFinClientNum;
    }

    public void setRetxFinClientNum(int retxFinClientNum) {
        this.retxFinClientNum = retxFinClientNum;
    }

    public int getRetxFinServerNum() {
        return retxFinServerNum;
    }

    public void setRetxFinServerNum(int retxFinServerNum) {
        this.retxFinServerNum = retxFinServerNum;
    }

    public int getOutOfOrderNum() {
        return outOfOrderNum;
    }

    public void setOutOfOrderNum(int outOfOrderNum) {
        this.outOfOrderNum = outOfOrderNum;
    }

    public int getOutOfOrderClientNum() {
        return outOfOrderClientNum;
    }

    public void setOutOfOrderClientNum(int outOfOrderClientNum) {
        this.outOfOrderClientNum = outOfOrderClientNum;
    }

    public int getOutOfOrderServerNum() {
        return outOfOrderServerNum;
    }

    public void setOutOfOrderServerNum(int outOfOrderServerNum) {
        this.outOfOrderServerNum = outOfOrderServerNum;
    }

    public int getZeroWindowNum() {
        return zeroWindowNum;
    }

    public void setZeroWindowNum(int zeroWindowNum) {
        this.zeroWindowNum = zeroWindowNum;
    }

    public int getZeroWindowClientNum() {
        return zeroWindowClientNum;
    }

    public void setZeroWindowClientNum(int zeroWindowClientNum) {
        this.zeroWindowClientNum = zeroWindowClientNum;
    }

    public int getZeroWindowServerNum() {
        return zeroWindowServerNum;
    }

    public void setZeroWindowServerNum(int zeroWindowServerNum) {
        this.zeroWindowServerNum = zeroWindowServerNum;
    }

    public int getSegLostNum() {
        return segLostNum;
    }

    public void setSegLostNum(int segLostNum) {
        this.segLostNum = segLostNum;
    }

    public int getSegLostClientNum() {
        return segLostClientNum;
    }

    public void setSegLostClientNum(int segLostClientNum) {
        this.segLostClientNum = segLostClientNum;
    }

    public int getSegLostServerNum() {
        return segLostServerNum;
    }

    public void setSegLostServerNum(int segLostServerNum) {
        this.segLostServerNum = segLostServerNum;
    }

    public int getMinClientWindow() {
        return minClientWindow;
    }

    public void setMinClientWindow(int minClientWindow) {
        this.minClientWindow = minClientWindow;
    }

    public int getMinServerWindow() {
        return minServerWindow;
    }

    public void setMinServerWindow(int minServerWindow) {
        this.minServerWindow = minServerWindow;
    }

    public int getReqNum() {
        return reqNum;
    }

    public void setReqNum(int reqNum) {
        this.reqNum = reqNum;
    }

    public int getRespNum() {
        return respNum;
    }

    public void setRespNum(int respNum) {
        this.respNum = respNum;
    }

    public int getReqTransferTime() {
        return reqTransferTime;
    }

    public void setReqTransferTime(int reqTransferTime) {
        this.reqTransferTime = reqTransferTime;
    }

    public int getRespTransferTime() {
        return respTransferTime;
    }

    public void setRespTransferTime(int respTransferTime) {
        this.respTransferTime = respTransferTime;
    }

    public int getSuccessfulTrans() {
        return successfulTrans;
    }

    public void setSuccessfulTrans(int successfulTrans) {
        this.successfulTrans = successfulTrans;
    }

    public int getQuickTransResp() {
        return quickTransResp;
    }

    public void setQuickTransResp(int quickTransResp) {
        this.quickTransResp = quickTransResp;
    }

    public int getNormalTransResp() {
        return normalTransResp;
    }

    public void setNormalTransResp(int normalTransResp) {
        this.normalTransResp = normalTransResp;
    }

    public int getSlowTransResp() {
        return slowTransResp;
    }

    public void setSlowTransResp(int slowTransResp) {
        this.slowTransResp = slowTransResp;
    }

    public int getPeakTransRespTime() {
        return peakTransRespTime;
    }

    public void setPeakTransRespTime(int peakTransRespTime) {
        this.peakTransRespTime = peakTransRespTime;
    }

    public int getTransRespTime() {
        return transRespTime;
    }

    public void setTransRespTime(int transRespTime) {
        this.transRespTime = transRespTime;
    }

    public int getRetxDelayClientTime() {
        return retxDelayClientTime;
    }

    public void setRetxDelayClientTime(int retxDelayClientTime) {
        this.retxDelayClientTime = retxDelayClientTime;
    }

    public int getRetxDelayServerTime() {
        return retxDelayServerTime;
    }

    public void setRetxDelayServerTime(int retxDelayServerTime) {
        this.retxDelayServerTime = retxDelayServerTime;
    }

    public int getDupAckNum() {
        return dupAckNum;
    }

    public void setDupAckNum(int dupAckNum) {
        this.dupAckNum = dupAckNum;
    }

    public int getDupAckClientNum() {
        return dupAckClientNum;
    }

    public void setDupAckClientNum(int dupAckClientNum) {
        this.dupAckClientNum = dupAckClientNum;
    }

    public int getDupAckServerNum() {
        return dupAckServerNum;
    }

    public void setDupAckServerNum(int dupAckServerNum) {
        this.dupAckServerNum = dupAckServerNum;
    }

    public int getZeroWinWaitClientTime() {
        return zeroWinWaitClientTime;
    }

    public void setZeroWinWaitClientTime(int zeroWinWaitClientTime) {
        this.zeroWinWaitClientTime = zeroWinWaitClientTime;
    }

    public int getZeroWinWaitServerTime() {
        return zeroWinWaitServerTime;
    }

    public void setZeroWinWaitServerTime(int zeroWinWaitServerTime) {
        this.zeroWinWaitServerTime = zeroWinWaitServerTime;
    }

    public int getDataAckClientNum() {
        return dataAckClientNum;
    }

    public void setDataAckClientNum(int dataAckClientNum) {
        this.dataAckClientNum = dataAckClientNum;
    }

    public int getDataAckServerNum() {
        return dataAckServerNum;
    }

    public void setDataAckServerNum(int dataAckServerNum) {
        this.dataAckServerNum = dataAckServerNum;
    }

    public int getAckDelayClientTime() {
        return ackDelayClientTime;
    }

    public void setAckDelayClientTime(int ackDelayClientTime) {
        this.ackDelayClientTime = ackDelayClientTime;
    }

    public int getAckDelayServerTime() {
        return ackDelayServerTime;
    }

    public void setAckDelayServerTime(int ackDelayServerTime) {
        this.ackDelayServerTime = ackDelayServerTime;
    }

    public int getPingRequestNum() {
        return pingRequestNum;
    }

    public void setPingRequestNum(int pingRequestNum) {
        this.pingRequestNum = pingRequestNum;
    }

    public int getPingReplyNum() {
        return pingReplyNum;
    }

    public void setPingReplyNum(int pingReplyNum) {
        this.pingReplyNum = pingReplyNum;
    }

    public int getPeakPingRespTime() {
        return peakPingRespTime;
    }

    public void setPeakPingRespTime(int peakPingRespTime) {
        this.peakPingRespTime = peakPingRespTime;
    }

    public int getAvgPingRespTime() {
        return avgPingRespTime;
    }

    public void setAvgPingRespTime(int avgPingRespTime) {
        this.avgPingRespTime = avgPingRespTime;
    }

    public int getRuleServerIpSiteId() {
        return ruleServerIpSiteId;
    }

    public void setRuleServerIpSiteId(int ruleServerIpSiteId) {
        this.ruleServerIpSiteId = ruleServerIpSiteId;
    }

    public long getSessStartTime() {
        return sessStartTime;
    }

    public void setSessStartTime(long sessStartTime) {
        this.sessStartTime = sessStartTime;
    }

    public long getSessDurationTime() {
        return sessDurationTime;
    }

    public void setSessDurationTime(long sessDurationTime) {
        this.sessDurationTime = sessDurationTime;
    }

    public String getClientIpStr() {
        return clientIpStr;
    }

    public void setClientIpStr(String clientIpStr) {
        this.clientIpStr = clientIpStr;
    }

    public String getServerIpStr() {
        return serverIpStr;
    }

    public void setServerIpStr(String serverIpStr) {
        this.serverIpStr = serverIpStr;
    }

    public String getProbeIpStr() {
        return probeIpStr;
    }

    public void setProbeIpStr(String probeIpStr) {
        this.probeIpStr = probeIpStr;
    }

    public int getInstId() {
        return instId;
    }

    public void setInstId(int instId) {
        this.instId = instId;
    }

    public String getMetaType() {
        return metaType;
    }

    public void setMetaType(String metaType) {
        this.metaType = metaType;
    }
}
