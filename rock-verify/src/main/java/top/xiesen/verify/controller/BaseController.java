package top.xiesen.verify.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import top.xiesen.verify.pojo.JsonResult;

import java.io.Serializable;

/**
 * @param <T>
 * @param <ID>
 * @author 谢森
 */
public interface BaseController<T, ID extends Serializable> {

    /**
     * 添加
     *
     * @param t
     * @return
     * @throws Exception
     */
    JsonResult add(@RequestBody T t) throws Exception;

    /**
     * 更新
     *
     * @param t
     * @return
     */
    JsonResult update(@RequestBody T t);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    JsonResult delete(@PathVariable(value = "id", required = true) ID id);

    /**
     * 根据 id 查询
     *
     * @param id
     * @return
     */
    JsonResult findById(@PathVariable(value = "id", required = true) ID id);

    /**
     * 查询所有
     *
     * @return
     */
    JsonResult list();

    /**
     * 分页查询
     *
     * @param page     页码
     * @param pageSize 一页显示几条
     * @return
     */
    JsonResult listPaged(@PathVariable(value = "page", required = true) int page
            , @PathVariable(value = "pageSize", required = true) int pageSize);
}
