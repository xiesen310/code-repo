package top.xiesen.verify.service;

import top.xiesen.verify.pojo.JsonResult;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @param <ID>
 * @author 谢森
 */
public interface BaseCommonService<T, ID extends Serializable> {

    /**
     * 保存数据
     *
     * @param dto
     * @throws Exception
     */
    void save(T dto) throws Exception;

    /**
     * 删除数据
     *
     * @param dto
     * @return
     */
    JsonResult<ID> delete(T dto);

    /**
     * 根据 id 删除数据
     *
     * @param id
     * @return
     */
    JsonResult<ID> deleteById(ID id);


    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    JsonResult update(T dto);

    /**
     * 根据 id 查询数据
     *
     * @param id
     * @return
     */
    JsonResult<T> findById(ID id);

    /**
     * 查询所有
     *
     * @return
     */
    JsonResult<List<T>> findAll();

    /**
     * 分页查询所有
     *
     * @param page
     * @param pageSize
     * @return
     */
    JsonResult<List<T>> findAllPaged(Integer page, Integer pageSize);

}
