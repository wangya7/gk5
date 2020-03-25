package wang.bannong.gk5.test.mybatisx.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.cache.CacheManager;
import wang.bannong.gk5.cache.CacheResult;
import wang.bannong.gk5.test.mybatisx.common.Car;
import wang.bannong.gk5.test.mybatisx.common.ShiroUser;
import wang.bannong.gk5.test.mybatisx.mapper.ShiroUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CommonCtrl {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ShiroUserMapper masterShiroUserMapper;
    @Autowired
    private ShiroUserMapper slave1ShiroUserMapper;
    @Autowired
    private ShiroUserMapper slave2ShiroUserMapper;


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

    @GetMapping(value = "/rb", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject getRb() {
        log.info("personManager1 is not null ? {}", personManager1 != null);
        log.info("personManager2 is not null ? {}", personManager2 != null);

        ShiroUser record = new ShiroUser();
        record.setName("HG");
        record.setName("HG");
        record.setPasswd("HG");
        record.setRole("HG");
        record.setPermission("HG");
        masterShiroUserMapper.insert(record);

        QueryWrapper<ShiroUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", "HG");
        List<ShiroUser> shiroUsers = masterShiroUserMapper.selectList(queryWrapper);
        log.info("Rs={}", shiroUsers);
        ShiroUser shiroUser1 = slave1ShiroUserMapper.selectById(1);
        log.info("Rs={}", shiroUser1);
        ShiroUser shiroUser2 = slave2ShiroUserMapper.selectById(2);
        log.info("Rs={}", shiroUser2);
        return new JSONObject();
    }

    @GetMapping(value = "/rbspe", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject getRbspe() {
        ShiroUser user = slave1ShiroUserMapper.selectByName("HG1");
        log.info("user={}", user);
        JSONObject res = new JSONObject();
        res.put("user", user);
        return res;
    }

    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void pageTest() {
        LambdaQueryWrapper<ShiroUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ShiroUser::getName, "HG");
        Page<ShiroUser> page1 = new Page<>(1, 5);
        Page<ShiroUser> page2 = slave1ShiroUserMapper.selectPage(page1, wrapper);
        log.info("page1={}", JSONObject.toJSONString(page1));
        log.info("page2={}", JSONObject.toJSONString(page2));
    }


}
