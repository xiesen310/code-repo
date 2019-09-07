package top.xiesen.simulation.flinksql;

/**
 * kafka数据流表，数据是同花顺日志
 */
public class KafkaThslog {
    /** 日志集 */
    private String logtypename ;
    /** 客户号 */
    private String clientid ;
    /** 手机号 */
    private String phone ;
    /** 功能号 */
    private String functionid ;
    /** 耗时 */
    private Integer latency ;
    /** 访问地址 */
    private String url ;
    /** 访问时间 */
    private String timestamps ;
    /** 主机名 */
    private String hostname ;
    /** ip地址 */
    private String ip ;


    @Override
    public String toString() {
        return "KafkaThslog{" +
                "logtypename='" + logtypename + '\'' +
                ", clientid='" + clientid + '\'' +
                ", phone='" + phone + '\'' +
                ", functionid='" + functionid + '\'' +
                ", latency=" + latency +
                ", url='" + url + '\'' +
                ", timestamp='" + timestamps + '\'' +
                ", hostname='" + hostname + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    public String getLogtypename() {
        return logtypename;
    }

    public void setLogtypename(String logtypename) {
        this.logtypename = logtypename;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public Integer getLatency() {
        return latency;
    }

    public void setLatency(Integer latency) {
        this.latency = latency;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
