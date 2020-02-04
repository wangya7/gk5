package wang.bannong.gk5.test.shiro.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.bannong.gk5.test.common.ShiroUser;
import wang.bannong.gk5.test.mapper.ShiroUserMapper;
import wang.bannong.gk5.test.shiro.ResultMap;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public ResultMap getUser() {
        QueryWrapper<ShiroUser> wrapper = new QueryWrapper<>();
        List<ShiroUser> list = shiroUserMapper.selectList(wrapper);
        return ResultMap.success(list);
    }
}