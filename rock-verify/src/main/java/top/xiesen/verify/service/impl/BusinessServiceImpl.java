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


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Business dto) {
        businessMapper.insert(dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult<String> delete(Business dto) {
        String id = dto.getId();
        businessMapper.deleteByPrimaryKey(id);
        return JSONResult.ok(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult<String> deleteById(String id) {
        businessMapper.deleteByPrimaryKey(id);
        return JSONResult.ok(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Business dto) {
        businessMapper.updateByPrimaryKeySelective(dto);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JSONResult<Business> findById(String id) {
        return JSONResult.ok(businessMapper.selectByPrimaryKey(id));
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
            criteria.andLike("sys_name", "%" + dto.getSysName() + "%");
        }

        if (!StringUtils.isEmptyOrWhitespace(dto.getSysNickname())) {
            criteria.andLike("sys_nickname", "%" + dto.getSysNickname() + "%");
        }

        example.orderBy("create_time").desc();
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
