package top.xiesen.demo2.demo2.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

/**
 * @author 谢森
 * @Description TODO
 * @className top.xiesen.demo2.demo2.pojo.User
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 11:39 星期二
 */
@Data
public class User {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */

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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 版本
     */
    @Version
    private int version;

    /**
     * 逻辑删除标识: 0.未删除，1.已删除
     */
    @TableLogic
    @TableField(select = false)
    private int deleted;
}
