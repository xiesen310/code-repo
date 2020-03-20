package top.xiesen.verify.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.xiesen.verify.mapper.SysUserMapper;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.SysUser;
import top.xiesen.verify.service.SysUserService;
import top.xiesen.verify.utils.StringUtils;

import java.util.List;

/**
 * @author 谢森
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(SysUser dto) throws Exception {
        userMapper.insert(dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult<String> delete(SysUser dto) {
        String id = null;
        if (dto != null) {
            id = dto.getId();
        }
        if (id != null) {
            userMapper.deleteByPrimaryKey(id);
            return JsonResult.ok(id);
        } else {
            return JsonResult.errorMsg("SysUser id 不存在");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult<String> deleteById(String id) {
        if (id != null) {
            userMapper.deleteByPrimaryKey(id);
            return JsonResult.ok(id);
        } else {
            return JsonResult.errorMsg("SysUser id 不存在");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult update(SysUser dto) {
        try {
            String id = dto.getId();
            if (StringUtils.isEmpty(id)) {
                return JsonResult.errorMsg("更新数据 id 不存在");
            } else {
                userMapper.updateByPrimaryKeySelective(dto);
                return JsonResult.ok();
            }
        } catch (Exception e) {
            return JsonResult.errorMsg("更新 SysUser 数据失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JsonResult<SysUser> findById(String id) {
        if (id != null) {
            return JsonResult.ok(userMapper.selectByPrimaryKey(id));
        } else {
            return JsonResult.errorMsg("SysUser id 不存在");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JsonResult<List<SysUser>> findAll() {
        Example example = new Example(SysUser.class);
        List<SysUser> userList = userMapper.selectByExample(example);
        return JsonResult.ok(userList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JsonResult<List<SysUser>> findAllPaged(Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        List<SysUser> businessList = userMapper.selectByExample(example);
        return JsonResult.ok(businessList);
    }
}
