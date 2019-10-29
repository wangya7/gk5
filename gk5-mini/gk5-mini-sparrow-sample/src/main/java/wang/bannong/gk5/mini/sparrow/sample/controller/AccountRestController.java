package wang.bannong.gk5.mini.sparrow.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import wang.bannong.gk5.mini.sparrow.common.annotations.Resources;
import wang.bannong.gk5.mini.sparrow.common.utils.IpUtils;
import wang.bannong.gk5.mini.sparrow.sample.model.parm.AccountInfoPARM;
import wang.bannong.gk5.mini.sparrow.sample.model.parm.LoginPARM;
import wang.bannong.gk5.mini.sparrow.sample.model.parm.PasswordPARM;
import wang.bannong.gk5.mini.sparrow.enums.AuthTypeEnum;
import wang.bannong.gk5.mini.sparrow.framework.controller.SuperController;
import wang.bannong.gk5.mini.sparrow.framework.responses.ApiResponses;
import wang.bannong.gk5.mini.sparrow.model.dto.AdminDetailsDTO;
import wang.bannong.gk5.mini.sparrow.model.dto.MenuTreeDTO;
import wang.bannong.gk5.mini.sparrow.model.dto.TokenDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Admin;
import wang.bannong.gk5.mini.sparrow.service.IAdminService;
import wang.bannong.gk5.mini.sparrow.service.IMenuService;

/**
 * 账户 前端控制器
 */
@Api(tags = {"Account"}, description = "账号操作相关接口")
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class AccountRestController extends SuperController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IMenuService menuService;

    @Resources
    @ApiOperation("获取Token")
    @PostMapping("/token")
    public ApiResponses<TokenDTO> getToken(@RequestBody @Validated LoginPARM loginPARM) {
        Admin admin = adminService.login(loginPARM.getLoginName(), loginPARM.getPassword(), IpUtils.getIpAddr(request));
        TokenDTO tokenDTO = adminService.getToken(admin);
        return success(tokenDTO);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("清除Token")
    @DeleteMapping("/token")
    public ApiResponses<Void> removeToken() {
        return success(HttpStatus.NO_CONTENT);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String"),
    })
    @PutMapping("/password")
    public ApiResponses<Void> updatePassword(@RequestBody @Validated PasswordPARM passwordPARM) {
        adminService.updatePassword(currentUid(), passwordPARM.getOldPassword(), passwordPARM.getNewPassword());
        return success();
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("获取账户详情")
    @GetMapping("/info")
    public ApiResponses<AdminDetailsDTO> accountInfo() {
        Integer uid = currentUid();
        AdminDetailsDTO userDetails = adminService.getUserDetails(uid);
        return success(userDetails);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("修改账户信息")
    @PutMapping("/info")
    public ApiResponses<Void> accountInfo(@RequestBody @Validated AccountInfoPARM accountInfoPARM) {
        Integer uid = currentUid();
        Admin admin = accountInfoPARM.convert(Admin.class);
        admin.setId(uid);
        adminService.updateById(admin);
        return success();
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("获取账户菜单")
    @GetMapping("/menus")
    public ApiResponses<List<MenuTreeDTO>> menus() {
        List<MenuTreeDTO> menuTrees = menuService.getUserPermMenus(currentUid());
        return success(menuTrees);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("获取账户按钮")
    @GetMapping("/buttons/aliases")
    public ApiResponses<Set<String>> buttonsAliases() {
        return success(menuService.getUserPermButtonAliases(currentUid()));
    }
}

