package wang.bannong.gk5.small.service.api;

import wang.bannong.gk5.small.biz.service.ApiUserService;
import wang.bannong.gk5.small.common.annotation.IgnoreAuth;
import wang.bannong.gk5.small.common.utils.R;
import wang.bannong.gk5.small.common.validator.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 注册
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @gitee https://gitee.com/fuyang_lipengjun/platform
 * @date 2017-03-26 17:27
 */
@Api(tags = "注册")
@RestController
@RequestMapping("/api/register")
public class ApiRegisterController {
    @Autowired
    private ApiUserService userService;

    /**
     * 注册
     */
    @ApiOperation(value = "注册")
    @IgnoreAuth
    @PostMapping("register")
    public R register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.save(mobile, password);

        return R.ok();
    }
}
