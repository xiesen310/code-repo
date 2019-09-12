package top.xiesen.rock.admin.service;

import top.xiesen.rock.admin.model.User;
import top.xiesen.rock.admin.model.dto.RespDto;

/**
 * 用户服务
 */
public interface UserService {
    RespDto getUserById(Integer id);

    RespDto deleteUserById(Integer userId);

    RespDto deleteUserByIds(Integer[] ids);

    RespDto updateUser(User user);

    RespDto insertUser(User user);

    RespDto login(User user);
}
