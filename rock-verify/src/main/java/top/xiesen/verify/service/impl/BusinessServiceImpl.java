package top.xiesen.verify.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.xiesen.verify.mapper.BusinessMapper;
import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JSONResult;
import top.xiesen.verify.service.BusinessService;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 此方法尽量不要使用，保存数据不检查 sys_name 是否存在
     *
     * @param dto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Business dto) {
        businessMapper.insert(dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult<String> delete(Business dto) {
        String id = null;
        if (dto != null) {
            id = dto.getId();
        }

        if (id != null) {
            businessMapper.deleteByPrimaryKey(id);
            return JSONResult.ok(id);
        } else {
            return JSONResult.errorMsg("Business id 不存在");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult<String> deleteById(String id) {
        if (id != null) {
            businessMapper.deleteByPrimaryKey(id);
            return JSONResult.ok(id);
        } else {
            return JSONResult.errorMsg("Business id 不存在");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Business dto) {
        businessMapper.updateByPrimaryKeySelective(dto);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JSONResult<Business> findById(String id) {
        if (id != null) {
            return JSONResult.ok(businessMapper.selectByPrimaryKey(id));
        } else {
            return JSONResult.errorMsg("Business id 不存在");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JSONResult<List<Business>> findAll(Business dto) {
        Example example = new Example(Business.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(dto.getSysName())) {
            criteria.andLike("sys_name", "%" + dto.getSysName() + "%");
        }

        if (!StringUtils.isEmptyOrWhitespace(dto.getSysNickname())) {
            criteria.andLike("sys_nickname", "%" + dto.getSysNickname() + "%");
        }

        List<Business> businessList = businessMapper.selectByExample(example);
        return JSONResult.ok(businessList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JSONResult<List<Business>> findAllPaged(Business dto, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(Business.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(dto.getSysName())) {
            criteria.andLike("sysName", "%" + dto.getSysName() + "%");
        }

        if (!StringUtils.isEmptyOrWhitespace(dto.getSysNickname())) {
            criteria.andLike("sysNickname", "%" + dto.getSysNickname() + "%");
        }

        example.orderBy("createTime").desc();
        List<Business> businessList = businessMapper.selectByExample(example);
        return JSONResult.ok(businessList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBusiness(Business business) {
        if (business != null) {
            String sysName = business.getSysName();
            List<Business> businessList = findBusinessBySysName(sysName);
            if (businessList.size() <= 0) {
                save(business);
            } else {
                System.out.println("插入数据失败");
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Business> findBusinessBySysName(String sysName) {
        Example example = new Example(Business.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(sysName)) {
            criteria.andEqualTo("sysName", sysName);
        }

        List<Business> businesses = businessMapper.selectByExample(example);
        return businesses;
    }
}
