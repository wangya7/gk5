package wang.bannong.gk5.test.mybatisx.shiro.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

import wang.bannong.gk5.test.mybatisx.common.ShiroUser;
import wang.bannong.gk5.test.mybatisx.mapper.ShiroUserMapper;
import wang.bannong.gk5.test.mybatisx.shiro.JWTUtil;
import wang.bannong.gk5.test.mybatisx.shiro.ResultMap;

@RestController
public class LoginController {

    @Autowired
    private ShiroUserMapper masterShiroUserMapper;

    @PostMapping("/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {

        QueryWrapper<ShiroUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        String realPassword = masterShiroUserMapper.selectOne(wrapper).getPasswd();
        if (realPassword == null) {
            return ResultMap.fail("用户名错误");
        } else if (!realPassword.equals(password)) {
            return ResultMap.fail("密码错误");
        } else {
            return ResultMap.success(JWTUtil.createToken(username));
        }
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return ResultMap.success(message);
    }
}
