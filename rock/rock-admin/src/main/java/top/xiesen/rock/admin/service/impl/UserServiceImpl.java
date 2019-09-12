package top.xiesen.rock.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xiesen.rock.admin.dao.UserMapper;
import top.xiesen.rock.admin.model.User;
import top.xiesen.rock.admin.model.dto.RespDto;
import top.xiesen.rock.admin.service.UserService;
import top.xiesen.rock.admin.utils.RespHelper;
import top.xiesen.rock.common.constant.CodeTable;
import top.xiesen.rock.common.utils.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public RespDto getUserById(Integer id) {
        if (id <= 0) {
            return RespHelper.fail(CodeTable.PARAM_ERR);
        }
        return RespHelper.ok(userMapper.selectUserById(id));
    }

    @Override
    public RespDto deleteUserById(Integer userId) {
        if (userId <= 0) {
            return RespHelper.fail(CodeTable.PARAM_ERR);
        }
        return RespHelper.ok(userMapper.deleteUserById(userId));
    }

    @Override
    public RespDto deleteUserByIds(Integer[] ids) {
        if (ids.length <= 0) {
            return RespHelper.fail(CodeTable.PARAM_ERR);
        }
        return RespHelper.ok(userMapper.deleteUserByIds(ids));
    }

    @Override
    public RespDto updateUser(User user) {
        if (null == user) {
            return RespHelper.fail(CodeTable.PARAM_ERR);
        }
        return RespHelper.ok(userMapper.updateUser(user));
    }

    @Override
    public RespDto insertUser(User user) {
        if (null == user) {
            return RespHelper.fail(CodeTable.PARAM_ERR);
        }
        return RespHelper.ok(userMapper.insertUser(user));
    }

    @Override
    public RespDto login(User user) {
        if (null == user) {
            return RespHelper.fail(CodeTable.PARAM_ERR);
        }
        String loginName = user.getLoginName();
        String password = user.getPassword();

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return RespHelper.fail(CodeTable.PARAM_ERR, "用户名不能为空");
        }
        User user1 = userMapper.selectUserByLoginName(loginName);
        if (user1.getPassword().equals(password)) {
            RespHelper.ok(user1);
        }
        return RespHelper.fail(CodeTable.FAILURE, "用户名或密码错误");
    }

}
