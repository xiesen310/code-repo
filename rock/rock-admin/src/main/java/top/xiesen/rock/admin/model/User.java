package top.xiesen.rock.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户 model 类
 * @author 谢森
 */
@Getter
@Setter
@ToString
public class User {
    /**
     * 用户 Id
     */
    private Integer userId;

    /**
     * 部门 Id
     */
    private Integer deptId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * email
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 性别
     */
    private char sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录时间
     */
    private Date loginDate;

    /**
     * 备注
     */
    private String remark;

}
