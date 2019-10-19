package top.xiesen.verify.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.xiesen.verify.mapper.SysUserMapper;
import top.xiesen.verify.mapper.SysUserMapperCustom;
import top.xiesen.verify.pojo.SysUser;
import top.xiesen.verify.service.SysUserService;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserMapperCustom sysUserMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(SysUser user) throws Exception {
        sysUserMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserById(String userId) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserList(SysUser user) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
//			criteria.andEqualTo("username", user.getUsername());
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }

        if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }

        List<SysUser> userList = sysUserMapper.selectByExample(example);

        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        example.orderBy("registTime").desc();
        List<SysUser> userList = sysUserMapper.selectByExample(example);

        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserByIdCustom(String userId) {
        List<SysUser> userList = sysUserMapperCustom.queryUserSimplyInfoById(userId);

        if (userList != null && !userList.isEmpty()) {
            return (SysUser) userList.get(0);
        }

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserTransactional(SysUser user) {
        sysUserMapper.insert(user);

        int a = 1 / 0;

        user.setIsDelete(1);
        sysUserMapper.updateByPrimaryKeySelective(user);
    }
}
