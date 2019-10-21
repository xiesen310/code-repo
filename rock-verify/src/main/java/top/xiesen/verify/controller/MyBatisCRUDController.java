package top.xiesen.verify.controller;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiesen.verify.pojo.JSONResult;
import top.xiesen.verify.pojo.SysUser;
import top.xiesen.verify.service.SysUserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private Sid sid;

    @RequestMapping("/saveUser")
    public JSONResult saveUser() throws Exception {
        String userId = sid.nextShort();
        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("xiesen" + new Date());
        user.setNickname("xiesen" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        sysUserService.saveUser(user);
        return JSONResult.ok("保存成功");
    }

    @RequestMapping("/updateUser")
    public JSONResult updateUser() {
        SysUser user = new SysUser();
        user.setId("1001");
        user.setUsername("1001-updated" + new Date());
        user.setNickname("1001-updated" + new Date());
        user.setPassword("1001-updated");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        sysUserService.updateUser(user);
        return JSONResult.ok("保存成功");
    }

    @RequestMapping("/deleteUser")
    public JSONResult deleteUser(String userId) {
        sysUserService.deleteUser(userId);
        return JSONResult.ok("删除成功");
    }

    @RequestMapping("/queryUserById")
    public JSONResult queryUserById(String userId) {
        return JSONResult.ok(sysUserService.queryUserById(userId));
    }

    @RequestMapping("/queryUserList")
    public JSONResult queryUserList() {
        SysUser user = new SysUser();
//        user.setUsername("xiesen");
//        user.setNickname("Allen");
        List<SysUser> userList = sysUserService.queryUserList(user);
        return JSONResult.ok(userList);
    }


    @RequestMapping("/queryUserListPaged")
    public JSONResult queryUserListPaged(Integer page) {

        if (page == null) {
            page = 1;
        }

        int pageSize = 10;

        SysUser user = new SysUser();
//		user.setNickname("lee");

        List<SysUser> userList = sysUserService.queryUserListPaged(user, page, pageSize);

        return JSONResult.ok(userList);
    }

    @RequestMapping("/queryUserByIdCustom")
    public JSONResult queryUserByIdCustom(String userId) {
        return JSONResult.ok(sysUserService.queryUserByIdCustom(userId));
    }

    @RequestMapping("/saveUserTransactional")
    public JSONResult saveUserTransactional() {

        String userId = sid.nextShort();

        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("lee" + new Date());
        user.setNickname("lee" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        sysUserService.saveUserTransactional(user);

        return JSONResult.ok("保存成功");
    }
}
