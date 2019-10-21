package top.xiesen.verify.pojo;

import java.util.Date;
import javax.persistence.*;

public class Business {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 业务简称(符合命名规范,数据库中唯一)
     */
    @Column(name = "sys_name")
    private String sysName;

    /**
     * 业务名称
     */
    @Column(name = "sys_nickname")
    private String sysNickname;

    /**
     * 系统描述信息
     */
    private String description;

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
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取业务简称(符合命名规范,数据库中唯一)
     *
     * @return sys_name - 业务简称(符合命名规范,数据库中唯一)
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * 设置业务简称(符合命名规范,数据库中唯一)
     *
     * @param sysName 业务简称(符合命名规范,数据库中唯一)
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    /**
     * 获取业务名称
     *
     * @return sys_nickname - 业务名称
     */
    public String getSysNickname() {
        return sysNickname;
    }

    /**
     * 设置业务名称
     *
     * @param sysNickname 业务名称
     */
    public void setSysNickname(String sysNickname) {
        this.sysNickname = sysNickname;
    }

    /**
     * 获取系统描述信息
     *
     * @return description - 系统描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置系统描述信息
     *
     * @param description 系统描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}