package top.xiesen.verify.pojo;

import javax.persistence.*;

/**
 * @author 谢森
 */
@Table(name = "sys_user")
public class SysUser {
    @Id
    private String id;

    /**
     * 用户名，登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     * 0：女
     * 1：男
     * 2：保密
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户名，登录名
     *
     * @return username - 用户名，登录名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名，登录名
     *
     * @param username 用户名，登录名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别
     * 0：女
     * 1：男
     * 2：保密
     *
     * @return sex - 性别
     * 0：女
     * 1：男
     * 2：保密
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     * 0：女
     * 1：男
     * 2：保密
     *
     * @param sex 性别
     *            0：女
     *            1：男
     *            2：保密
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}