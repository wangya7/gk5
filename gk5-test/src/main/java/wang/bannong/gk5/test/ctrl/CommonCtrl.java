package wang.bannong.gk5.test.ctrl;

import com.alibaba.fastjson.JSONObject;
import wang.bannong.gk5.cache.CacheManager;
import wang.bannong.gk5.cache.CacheResult;
import wang.bannong.gk5.test.common.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonCtrl {

    @Autowired
    private CacheManager cacheManager;

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
}
