package com.example.demo1.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Description 用户数据库对象
 * @className com.example.demo1.pojo.User
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/25 11:42 星期一
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = -9097822896502889640L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */

    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 直属上级
     */
    private Long managerId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
