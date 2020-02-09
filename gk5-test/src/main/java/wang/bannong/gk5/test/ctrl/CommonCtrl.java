package wang.bannong.gk5.test.ctrl;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.cache.CacheManager;
import wang.bannong.gk5.cache.CacheResult;
import wang.bannong.gk5.test.common.Car;
import wang.bannong.gk5.test.common.ShiroUser;
import wang.bannong.gk5.test.mapper.ShiroUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommonCtrl {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ShiroUserMapper masterShiroUserMapper;
    @Autowired
    private ShiroUserMapper slaveShiroUserMapper;


    @GetMapping(value = "/queryUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject queryUser() {
        JSONObject obj = new JSONObject();
        obj.put("user", masterShiroUserMapper.selectById(3L));
        return obj;
    }



    @GetMapping(value = "/put", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject put() {
        JSONObject obj = new JSONObject();
        obj.put("name", "will");
        obj.put("age", 23);
        Car car = new Car("FIT", 80000L);
        obj.put("car", car);
        cacheManager.put("json", obj);
        return obj;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject get() {
        CacheResult<JSONObject> cacheResult = cacheManager.getObject("json");
        if (cacheResult.isSucc()) {
            JSONObject obj = cacheResult.getModule();
            System.out.println(obj);
            return obj;
        }
        return new JSONObject();
    }

    @Autowired
    private WillRegisterBeanBO personManager1;
    @Autowired
    private WillRegisterBeanBO personManager2;
    @Autowired
    private WillRegisterBeanBO personManager3;

    @GetMapping(value = "/rb", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject getRb() {
        log.info("personManager1 is not null ? {}", personManager1 != null);
        log.info("personManager2 is not null ? {}", personManager2 != null);
        log.info("personManager3 is not null ? {}", personManager3 != null);

        ShiroUser record = new ShiroUser();
        record.setId(11);
        record.setName("HG");
        record.setPasswd("HG");
        record.setRole("HG");
        record.setPermission("HG");
        masterShiroUserMapper.insert(record);

        ShiroUser shiroUser = slaveShiroUserMapper.selectById(11);
        log.info("Rs={}", shiroUser);
        return new JSONObject();
    }
}
