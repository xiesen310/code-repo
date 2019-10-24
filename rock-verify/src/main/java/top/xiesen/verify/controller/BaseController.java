package top.xiesen.verify.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import top.xiesen.verify.pojo.JSONResult;

import java.io.Serializable;

public interface BaseController<T, ID extends Serializable> {
    /**
     * 添加
     *
     * @param t
     * @return
     */
    JSONResult add(@RequestBody T t) throws Exception;

    /**
     * 更新
     *
     * @param t
     * @return
     */
    JSONResult update(@RequestBody T t);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    JSONResult delete(@PathVariable(value = "id", required = true) ID id);

    /**
     * 根据 id 查询
     *
     * @param id
     * @return
     */
    JSONResult findById(@PathVariable(value = "id", required = true) ID id);

    /**
     * 查询所有
     *
     * @return
     */
    JSONResult list();

    /**
     * 分页查询
     *
     * @param page     页码
     * @param pageSize 一页显示几条
     * @return
     */
    JSONResult listPaged(@PathVariable(value = "page", required = true) int page
            , @PathVariable(value = "pageSize", required = true) int pageSize);
}
