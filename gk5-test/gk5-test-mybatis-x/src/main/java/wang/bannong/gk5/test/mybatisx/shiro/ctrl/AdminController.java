package wang.bannong.gk5.test.mybatisx.shiro.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.bannong.gk5.test.mybatisx.common.ShiroUser;
import wang.bannong.gk5.test.mybatisx.mapper.ShiroUserMapper;
import wang.bannong.gk5.test.mybatisx.shiro.ResultMap;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ShiroUserMapper masterShiroUserMapper;

    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public ResultMap getUser() {
        QueryWrapper<ShiroUser> wrapper = new QueryWrapper<>();
        List<ShiroUser> list = masterShiroUserMapper.selectList(wrapper);
        return ResultMap.success(list);
    }
}