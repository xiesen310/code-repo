package com.example.demo1.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 谢森
 * @Description 用户映射接口
 * @className com.example.demo1.dao.UserMapper
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/25 11:46 星期一
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询所有
     * @Select("select * from user ${ew.customSqlSegment}")
     * @param wrapper Wrapper<User>
     * @return List<User>
     */
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 自定义分页查询
     * @param page page 对象
     * @param wrapper Wrapper<User>
     * @return IPage<User>
     */
    IPage<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
