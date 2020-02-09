package wang.bannong.gk5.test.shiro.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.bannong.gk5.test.common.ShiroUser;
import wang.bannong.gk5.test.mapper.ShiroUserMapper;
import wang.bannong.gk5.test.shiro.ResultMap;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private ShiroUserMapper masterShiroUserMapper;

    /**
     * 拥有 user, admin 角色的用户可以访问下面的页面
     */
    @GetMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getMessage() {
        return ResultMap.success("成功获得信息！");
    }

    @PostMapping("/updatePassword")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap updatePassword(String username, String oldPassword, String newPassword) {
        QueryWrapper<ShiroUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        ShiroUser user = masterShiroUserMapper.selectOne(wrapper);
        String dataBasePassword = user.getPasswd();
        if (dataBasePassword.equals(oldPassword)) {
            user.setPasswd(newPassword);
            masterShiroUserMapper.updateById(user);
        } else {
            return ResultMap.fail("密码错误！");
        }
        return ResultMap.success("成功获得信息！");
    }

    /**
     * 拥有 vip 权限可以访问该页面
     */
    @GetMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public ResultMap getVipMessage() {
        return ResultMap.success("成功获得 vip 信息！");
    }

}