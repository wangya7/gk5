package wang.bannong.gk5.test.shiro.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wang.bannong.gk5.test.shiro.ResultMap;

@RestController
@RequestMapping("/user")
public class UserController{

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return ResultMap.success("您拥有用户权限，可以获得该接口的信息！");
    }
}