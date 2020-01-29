package wang.bannong.gk5.test.shiro.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wang.bannong.gk5.test.shiro.ResultMap;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return ResultMap.success().message("您拥有管理员权限，可以获得该接口的信息！");
    }
}