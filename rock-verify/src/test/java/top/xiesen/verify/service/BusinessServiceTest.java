package top.xiesen.verify.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xiesen.verify.exception.DatabaseInsertException;
import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.service.impl.BusinessServiceImpl;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class BusinessServiceTest {

    @Autowired
    private BusinessServiceImpl businessServiceImpl;

    @Autowired
    private Sid sid;

    @Test
    public void saveBusiness() throws DatabaseInsertException {
        String id = sid.nextShort();
        Business business = new Business();
        business.setId(id);
        business.setCreateBy("admin");
        business.setSysName("test1");
        business.setSysNickname("测试");
        business.setDescription("创建业务测试");
        business.setCreateTime(new Date());
        businessServiceImpl.saveBusiness(business);
    }

    @Test
    public void findAll() {
        JsonResult<List<Business>> all = businessServiceImpl.findAll();
        System.out.println(all);
    }

    @Test
    public void findAllPaged() {
        Business business = new Business();
        JsonResult<List<Business>> allPaged = businessServiceImpl.findAllPaged(1, 2);
        System.out.println(allPaged);
    }

    @Test
    public void findById() {
        JsonResult<Business> result = businessServiceImpl.findById("1");
        System.out.println(result);
    }

    @Test
    public void save() {
        String id = sid.nextShort();
        Business business = new Business();
        business.setId(id);
        business.setCreateBy("admin");
        business.setSysName("test2");
        business.setSysNickname("测试2");
        business.setDescription("创建业务测试");
        business.setCreateTime(new Date());
        System.out.println(JSON.toJSON(business));
        businessServiceImpl.save(business);
    }

    @Test
    public void delete() {
        Business business = new Business();
        business.setId("191019H54T1YZ4SW");
        JsonResult<String> result = businessServiceImpl.delete(business);
        System.out.println(result.getStatus());
    }

    @Test
    public void deleteById() {
        JsonResult<String> result = businessServiceImpl.deleteById("191019H54T1YZ4SW");
        System.out.println(result.getMsg());
    }


}
