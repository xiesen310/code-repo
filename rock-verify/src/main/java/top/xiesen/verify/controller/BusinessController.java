package top.xiesen.verify.controller;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JSONResult;
import top.xiesen.verify.service.BusinessService;

import java.util.Date;

@RestController
@RequestMapping("business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private Sid sid;

    @RequestMapping("/saveBusiness")
    public JSONResult saveBusiness() throws Exception {
        String id = sid.nextShort();

        Business business = new Business();
        business.setId(id);
        business.setCreateBy("admin");
        business.setSysName("test");
        business.setSysNickname("测试");
        business.setDescription("创建业务测试");
        business.setCreateTime(new Date());

        businessService.saveBusiness(business);
//        businessService.save(business);
        return JSONResult.ok("保存成功");
    }

}
