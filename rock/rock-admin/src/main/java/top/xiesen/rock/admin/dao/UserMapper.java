package top.xiesen.rock.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import top.xiesen.rock.admin.model.User;

/**
 * 用户映射
 * @author 谢森
 */
@Mapper
public interface UserMapper {
    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    User selectUserById(Integer userId);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(Integer userId);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserByIds(Integer[] ids);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(User user);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(User user);

    /**
     * 根据用户名查询
     * @param userName 用户名
     * @return User
     */
    User selectUserByLoginName(String userName);
}
