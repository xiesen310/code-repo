package top.xiesen.verify.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.SysUser;
import top.xiesen.verify.service.SysUserService;

/**
 * @author 谢森
 */
@RestController
@RequestMapping("/api/v1/user")
@Api("用户操作接口")
public class SysUserController implements BaseController<SysUser, String> {
    @Autowired
    private SysUserService userService;

    @Autowired
    private Sid sid;

    @ApiOperation("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public JsonResult add(@RequestBody SysUser sysUser) throws Exception {
        String id = sid.nextShort();
        if (sysUser != null) {
            sysUser.setId(id);
            userService.save(sysUser);
            return JsonResult.ok("保存成功");
        } else {
            return JsonResult.errorMsg("保存失败");
        }
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Override
    public JsonResult update(@RequestBody SysUser sysUser) {
        return userService.update(sysUser);
    }

    @ApiOperation("根据 ID 删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @Override
    public JsonResult delete(@PathVariable(value = "id", required = true) String id) {
        return JsonResult.ok(userService.deleteById(id));
    }

    @ApiOperation("根据 ID 查询用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public JsonResult findById(@PathVariable(value = "id", required = true) String id) {
        return JsonResult.ok(userService.findById(id));
    }

    @ApiOperation("查询所有")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @Override
    public JsonResult list() {
        return userService.findAll();
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/{page}/{pageSize}", method = RequestMethod.GET)
    @Override
    public JsonResult listPaged(@PathVariable(value = "page", required = true) int page
            , @PathVariable(value = "pageSize", required = true) int pageSize) {
        return userService.findAllPaged(page, pageSize);
    }
}
