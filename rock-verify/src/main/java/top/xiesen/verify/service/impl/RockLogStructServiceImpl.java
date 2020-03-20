package top.xiesen.verify.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.xiesen.verify.constants.RockConstant;
import top.xiesen.verify.mapper.RockLogStructMapper;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.RockLogStruct;
import top.xiesen.verify.service.RockLogStructService;
import top.xiesen.verify.utils.StringUtils;

import java.util.List;

/**
 * @Description
 * @className top.xiesen.verify.service.impl.RockLogStructServiceImpl
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 21:50
 */
@Service
public class RockLogStructServiceImpl implements RockLogStructService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RockLogStructMapper rockLogStructMapper;

    @Override
    public void save(RockLogStruct dto) throws Exception {
        rockLogStructMapper.insert(dto);
    }

    @Override
    public JsonResult<String> delete(RockLogStruct dto) {
        String id = null;
        if (dto != null) {
            id = dto.getId();
        }

        if (id != null) {
            rockLogStructMapper.deleteByPrimaryKey(id);
            return JsonResult.ok(id);
        } else {
            return JsonResult.errorMsg("rockLogStruct id 不存在");
        }
    }

    @Override
    public JsonResult<String> deleteById(String id) {
        if (id != null) {
            rockLogStructMapper.deleteByPrimaryKey(id);
            return JsonResult.ok(id);
        } else {
            return JsonResult.errorMsg("rockLogStruct id 不存在");
        }
    }

    @Override
    public JsonResult update(RockLogStruct dto) {
        try {
            String id = dto.getId();
            if (StringUtils.isEmpty(id)) {
                return JsonResult.errorMsg("更新数据 id 不存在");
            } else {
                rockLogStructMapper.updateByPrimaryKeySelective(dto);
                return JsonResult.ok();
            }
        } catch (Exception e) {
            return JsonResult.errorMsg("更新 RockLogStruct 数据失败");
        }
    }

    @Override
    public JsonResult<RockLogStruct> findById(String id) {
        if (id != null) {
            return JsonResult.ok(rockLogStructMapper.selectByPrimaryKey(id));
        } else {
            return JsonResult.errorMsg("RockLogStruct id 不存在");
        }
    }


    @Override
    public JsonResult<List<RockLogStruct>> findAll() {
        Example example = new Example(RockLogStruct.class);
        List<RockLogStruct> rockLogStructList = rockLogStructMapper.selectByExample(example);
        return JsonResult.ok(rockLogStructList);
    }

    @Override
    public JsonResult<List<RockLogStruct>> findAllPaged(Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(RockLogStruct.class);
        Example.Criteria criteria = example.createCriteria();

        example.orderBy("createTime").desc();
        List<RockLogStruct> rockLogStructList = rockLogStructMapper.selectByExample(example);
        return JsonResult.ok(rockLogStructList);
    }

    @Override
    public JsonResult saveRockLogStruct(RockLogStruct rockLogStruct) throws Exception {
        if (rockLogStruct != null) {
            String name = rockLogStruct.getName();
            List<RockLogStruct> businessList = findRockLogStructByName(name);
            if (businessList.size() <= 0) {
                save(rockLogStruct);
                String key = RockConstant.ROCK_PREFIX + rockLogStruct.getId();
                String logStruct = rockLogStruct.getLogStruct();
                saveRedis(key, logStruct);
                return JsonResult.ok(name + "插入成功");
            } else {
                return JsonResult.errorMsg(name + " 已存在,插入 RockLogStruct 失败");
            }
        } else {
            return JsonResult.errorMsg("RockLogStruct 对象为空,插入 RockLogStruct 失败");
        }
    }


    /**
     * 保存数据到 redis 中
     *
     * @param key
     * @param value
     */
    private void saveRedis(String key, String value) {
        String values = searchRedis(key);
        if (null == values) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 查询 redis
     *
     * @param key
     * @return
     */
    private String searchRedis(String key) {
        String values = redisTemplate.opsForValue().get(key);
        return values;
    }


    @Override
    public List<RockLogStruct> findRockLogStructByName(String name) {
        Example example = new Example(RockLogStruct.class);
        Example.Criteria criteria = example.createCriteria();

        if (!org.thymeleaf.util.StringUtils.isEmptyOrWhitespace(name)) {
            criteria.andEqualTo("name", name);
        }

        List<RockLogStruct> rockLogStructList = rockLogStructMapper.selectByExample(example);
        return rockLogStructList;
    }

    @Override
    public String findLogStructById(String id) {
        return selectLogStructById(id);
    }

    /**
     * 1. 根据 id 去 redis 查询
     * 2. 如果在 redis 中查询到数据,直接返回
     * 3. 如果在 redis 中没有查询到数据，则去数据库中查询，并将查询到的数据保存到 redis 中
     */
    private String selectLogStructById(String id) {
        if (null != id) {
            String key = RockConstant.ROCK_PREFIX + id;
            String value = searchRedis(key);
            if (null != value) {
                return value;
            } else {
                RockLogStruct rockLogStruct = rockLogStructMapper.selectByPrimaryKey(id);
                if (null != rockLogStruct) {
                    String logStruct = rockLogStruct.getLogStruct();
                    saveRedis(RockConstant.ROCK_PREFIX + rockLogStruct.getId(), logStruct);
                    return logStruct;
                }
            }
        }
        return null;
    }
}
