package top.xiesen.verify.service;

import top.xiesen.verify.pojo.JSONResult;

import java.io.Serializable;
import java.util.List;

public interface BaseCommonService<T, ID extends Serializable> {

    /**
     * 保存数据
     *
     * @param dto
     * @return
     */
    void save(T dto) throws Exception;

    /**
     * 删除数据
     *
     * @param dto
     * @return
     */
    JSONResult<ID> delete(T dto);

    /**
     * 根据 id 删除数据
     *
     * @param id
     * @return
     */
    JSONResult<ID> deleteById(ID id);

    /**
     * 更新数据
     *
     * @param dto
     */
    JSONResult update(T dto);

    /**
     * 根据 id 查询数据
     *
     * @param id
     * @return
     */
    JSONResult<T> findById(ID id);

    /**
     * 查询所有
     *
     * @return
     */
    JSONResult<List<T>> findAll();

    /**
     * 分页查询所有
     *
     * @param page
     * @param pageSize
     * @return
     */
    JSONResult<List<T>> findAllPaged(Integer page, Integer pageSize);

}
