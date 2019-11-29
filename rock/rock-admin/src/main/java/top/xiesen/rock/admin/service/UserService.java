package top.xiesen.rock.admin.service;

import top.xiesen.rock.admin.model.User;
import top.xiesen.rock.admin.model.dto.RespDto;

/**
 * 用户服务
 * @author 谢森
 */
public interface UserService {
    /**
     * 根据 Id 查询用户
     * @param id ID
     * @return RespDto
     */
    RespDto getUserById(Integer id);

    /**
     * 根据 Id 删除
     * @param userId 用户 Id
     * @return RespDto
     */
    RespDto deleteUserById(Integer userId);

    /**
     * 根据 Id 批量删除
     * @param ids id 列表
     * @return RespDto
     */
    RespDto deleteUserByIds(Integer[] ids);

    /**
     * 更新用户
     * @param user 用户对象
     * @return RespDto
     */
    RespDto updateUser(User user);

    /**
     * 插入用户数据
     * @param user 用户对象
     * @return RespDto
     */
    RespDto insertUser(User user);

    /**
     * 登录
     * @param user 用户对象
     * @return RespDto
     */
    RespDto login(User user);
}
