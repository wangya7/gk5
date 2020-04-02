package wang.bannong.gk5.test.mybatisx.ctrl;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "n1")
public class N1TestCtrl {



    @RequestMapping(value = "/u1", method = RequestMethod.POST)
    public JSONObject u1() {
        JSONObject result = new JSONObject();
        result.put("path", "n1/u1");
        return result;
    }


    @RequestMapping(value = "/u2", method = RequestMethod.GET)
    public JSONObject u2() {
        JSONObject result = new JSONObject();
        result.put("path", "n1/u2");
        return result;
    }

    @RequestMapping(value = "/u2/r1", method = RequestMethod.GET)
    public JSONObject u2r1() {
        JSONObject result = new JSONObject();
        result.put("path", "n1/u2/r1");
        return result;
    }
}
