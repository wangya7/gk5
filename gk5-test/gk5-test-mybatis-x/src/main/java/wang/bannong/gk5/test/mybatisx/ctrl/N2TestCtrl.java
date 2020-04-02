package wang.bannong.gk5.test.mybatisx.ctrl;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "n2")
public class N2TestCtrl {

    @RequestMapping(value = "/u1", method = RequestMethod.POST)
    public JSONObject u1() {
        JSONObject result = new JSONObject();
        result.put("path", "n2/u1");
        return result;
    }


    @RequestMapping(value = "/u2", method = RequestMethod.GET)
    public JSONObject u2() {
        JSONObject result = new JSONObject();
        result.put("path", "n2/u2");
        return result;
    }

    @RequestMapping(value = "/u2/r1", method = RequestMethod.GET)
    public JSONObject u2r1() {
        JSONObject result = new JSONObject();
        result.put("path", "n2/u2/r1");
        return result;
    }
}
