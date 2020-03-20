package top.xiesen.verify.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.RockLogStruct;
import top.xiesen.verify.service.RockLogStructService;

import java.util.Date;

/**
 * @Description
 * @className top.xiesen.verify.controller.RockLogStructController
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 21:58
 */
@RestController
@RequestMapping("/api/v1/rockLogStruct")
@Api("业务操作接口")
public class RockLogStructController implements BaseController<RockLogStruct, String> {

    @Autowired
    private RockLogStructService rockLogStructService;

    @Autowired
    private Sid sid;

    @ApiOperation("添加日志集")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public JsonResult add(@RequestBody RockLogStruct rockLogStruct) throws Exception {
        String id = sid.nextShort();
        if (rockLogStruct != null) {
            rockLogStruct.setId(id);
            rockLogStruct.setCreateTime(new Date());
            rockLogStruct.setUpdateTime(new Date());
            rockLogStruct.setStatus(1);
            rockLogStructService.saveRockLogStruct(rockLogStruct);
            return JsonResult.ok("保存成功");
        } else {
            return JsonResult.errorMsg("保存失败");
        }
    }

    @ApiOperation("更新日志集")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Override
    public JsonResult update(@RequestBody RockLogStruct rockLogStruct) {
        return rockLogStructService.update(rockLogStruct);
    }

    @ApiOperation("根据 ID 删除日志集")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @Override
    public JsonResult delete(@PathVariable(value = "id", required = true) String id) {
        return rockLogStructService.deleteById(id);
    }

    @ApiOperation("根据 ID 查询日志集")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public JsonResult findById(@PathVariable(value = "id", required = true) String id) {
        return rockLogStructService.findById(id);
    }

    @ApiOperation("查询所有")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @Override
    public JsonResult list() {
        return rockLogStructService.findAll();
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/{page}/{pageSize}", method = RequestMethod.GET)
    @Override
    public JsonResult listPaged(@PathVariable(value = "page", required = true) int page
            , @PathVariable(value = "pageSize", required = true) int pageSize) {
        return rockLogStructService.findAllPaged(page, pageSize);
    }
}
