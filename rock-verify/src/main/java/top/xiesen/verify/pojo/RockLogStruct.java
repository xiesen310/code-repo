package top.xiesen.verify.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description
 * @className top.xiesen.verify.pojo.rockLogStruct
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 21:38
 */
@Data
public class RockLogStruct {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 日志集名称
     */
    private String name;

    /**
     * 状态: 0表示发布；1 表示未发布
     */
    @Column(name = "status")
    private int status;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 日志结构
     */
    @Column(name = "log_struct")
    private String logStruct;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 业务 id
     */
    @Column(name = "business_id")
    private String businessId;


}
