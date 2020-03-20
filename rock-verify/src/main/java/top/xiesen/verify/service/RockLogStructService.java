package top.xiesen.verify.service;

import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.RockLogStruct;

import java.util.List;

/**
 * @Description
 * @className top.xiesen.verify.service.RockLogStructService
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 21:49
 */
public interface RockLogStructService extends BaseCommonService<RockLogStruct, String> {

    /**
     * 保存数据
     *
     * @param rockLogStruct
     * @return
     */
    JsonResult saveRockLogStruct(RockLogStruct rockLogStruct) throws Exception;

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    List<RockLogStruct> findRockLogStructByName(String name);

    /**
     * 根据 id 查询 LogStruct
     *
     * @param id
     * @return
     */
    String findLogStructById(String id);
}
