package top.xiesen.verify.mapper;

import top.xiesen.verify.pojo.SysUser;

import java.util.List;

public interface SysUserMapperCustom {
    List<SysUser> queryUserSimplyInfoById(String id);
}
