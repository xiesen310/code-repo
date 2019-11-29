package top.xiesen.demo2.demo2.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.xiesen.demo2.demo2.pojo.User;

import java.util.List;

/**
 * @author 谢森
 * @Description 用户接口
 * @className top.xiesen.demo2.demo2.dao.UserMapper
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 11:42 星期二
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义查询
     * @param wrapper
     * @return
     */
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> mySelectList(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
