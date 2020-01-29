package wang.bannong.gk5.test.shiro.ctrl;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.bannong.gk5.test.common.ShiroUser;
import wang.bannong.gk5.test.dao.ShiroUserDao;
import wang.bannong.gk5.test.shiro.ResultMap;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ShiroUserDao shiroUserDao;

    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public ResultMap getUser() {
        ShiroUser bo = new ShiroUser();
        List<ShiroUser> list = shiroUserDao.selectByBo(bo, 1, Integer.MAX_VALUE);
        return ResultMap.success(list);
    }
}