package top.xiesen.verify.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.service.BusinessService;

import java.util.Date;

/**
 * GET（SELECT）：从服务器取出资源（一项或多项）。
 * POST（CREATE）：在服务器新建一个资源。
 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
 * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
 * DELETE（DELETE）：从服务器删除资源。
 *
 * @author 谢森
 */
@RestController
@RequestMapping("/api/v1/business")
@Api("业务操作接口")
public class BusinessController implements BaseController<Business, String> {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private Sid sid;


    @ApiOperation("添加业务")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public JsonResult add(@RequestBody Business business) {
        String id = sid.nextShort();
        if (business != null) {
            business.setId(id);
            business.setCreateTime(new Date());
            businessService.saveBusiness(business);
            return JsonResult.ok("保存成功");
        } else {
            return JsonResult.errorMsg("保存失败");
        }
    }

    @ApiOperation("更新业务")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Override
    public JsonResult update(@RequestBody Business business) {
        return businessService.update(business);
    }

    @ApiOperation("根据 ID 删除业务")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @Override
    public JsonResult delete(@PathVariable(value = "id", required = true) String id) {
        return businessService.deleteById(id);
    }

    @ApiOperation("根据 ID 查询业务")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public JsonResult findById(@PathVariable(value = "id", required = true) String id) {
        return businessService.findById(id);
    }

    @ApiOperation("查询所有")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @Override
    public JsonResult list() {
        return businessService.findAll();
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/{page}/{pageSize}", method = RequestMethod.GET)
    public JsonResult listPaged(@PathVariable(value = "page", required = true) int page
            , @PathVariable(value = "pageSize", required = true) int pageSize) {
        return businessService.findAllPaged(page, pageSize);
    }

}
