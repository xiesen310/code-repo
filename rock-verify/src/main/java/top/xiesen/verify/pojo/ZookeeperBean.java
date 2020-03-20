package top.xiesen.verify.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 谢森
 */
@Getter
@Setter
@ToString
public class ZookeeperBean {
    /**
     * zookeeper 的地址
     */
    private String zkUrl;

    /**
     * zookeeper 会话超时时间
     */
    private Integer sessionTimeout;

    /**
     * zookeeper 链接超时时间
     */
    private Integer connectionTimeout;

    /**
     * zookeeper 认证,此字段信息暂时未使用
     */
    private Boolean isZkSecurityEnabled;
}
