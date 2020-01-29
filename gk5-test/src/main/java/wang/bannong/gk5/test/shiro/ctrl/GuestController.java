package wang.bannong.gk5.test.shiro.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wang.bannong.gk5.test.shiro.ResultMap;

@RestController
@RequestMapping("/guest")
public class GuestController{

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return ResultMap.success("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap submitLogin() {
        return ResultMap.success("您拥有获得该接口的信息的权限！");
    }
}
