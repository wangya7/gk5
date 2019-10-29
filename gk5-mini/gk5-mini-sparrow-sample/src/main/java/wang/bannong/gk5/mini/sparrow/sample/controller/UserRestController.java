package wang.bannong.gk5.mini.sparrow.sample.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import wang.bannong.gk5.mini.sparrow.common.annotations.Resources;
import wang.bannong.gk5.mini.sparrow.sample.model.parm.AdminPARM;
import wang.bannong.gk5.mini.sparrow.enums.AuthTypeEnum;
import wang.bannong.gk5.mini.sparrow.enums.StatusEnum;
import wang.bannong.gk5.mini.sparrow.framework.controller.SuperController;
import wang.bannong.gk5.mini.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.mini.sparrow.framework.responses.ApiResponses;
import wang.bannong.gk5.mini.sparrow.framework.utils.ApiAssert;
import wang.bannong.gk5.mini.sparrow.model.dto.AdminDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Admin;
import wang.bannong.gk5.mini.sparrow.service.IAdminService;

/**
 * 系统用户表 前端控制器
 */
@Api(tags = {"User"}, description = "用户操作相关接口")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class UserRestController extends SuperController {

    @Autowired
    private IAdminService adminService;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "需要检查的账号", paramType = "query"),
            @ApiImplicitParam(name = "nickname", value = "需要检查的账号", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "需要检查的账号", paramType = "query")
    })
    @GetMapping
    public ApiResponses<IPage<AdminDTO>> page(@RequestParam(value = "loginName", required = false) String loginName,
                                              @RequestParam(value = "nickname", required = false) String nickname,
                                              @RequestParam(value = "status", required = false) StatusEnum status) {
        ;
        return success(
                adminService.query().likeRight(StringUtils.isNotEmpty(loginName), Admin::getLoginName, loginName)
                        .likeRight(StringUtils.isNotEmpty(nickname), Admin::getNickname, nickname)
                        .eq(Objects.nonNull(status), Admin::getStatus, status)
                        .page(this.<Admin>getPage())
                        .convert(e -> e.convert(AdminDTO.class))
        );
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @GetMapping("/{id}")
    public ApiResponses<AdminDTO> get(@PathVariable("id") Integer id) {
        Admin admin = adminService.getById(id);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, admin);
        AdminDTO adminDTO = admin.convert(AdminDTO.class);
        List<Integer> roleIds = adminService.getRoleIds(admin.getId());
        adminDTO.setRoleIds(roleIds);
        return success(adminDTO);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}/password")
    public ApiResponses<Void> resetPwd(@PathVariable("id") Integer id) {
        adminService.resetPwd(id);
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("设置用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}/status")
    public ApiResponses<Void> updateStatus(@PathVariable("id") Integer id, @RequestBody @Validated(AdminPARM.Status.class) AdminPARM adminPARM) {
        adminService.updateStatus(id, adminPARM.getStatus());
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("创建用户")
    @PostMapping
    public ApiResponses<Void> create(@RequestBody @Validated(AdminPARM.Create.class) AdminPARM adminPARM) {
        int count = adminService.query().eq(Admin::getLoginName, adminPARM.getLoginName()).count();
        ApiAssert.isTrue(ErrorCodeEnum.USERNAME_ALREADY_EXISTS, count == 0);
        Admin admin = adminPARM.convert(Admin.class);
        //没设置密码 设置默认密码
        if (StringUtils.isEmpty(admin.getPassword())) {
            admin.setPassword(Md5Crypt.apr1Crypt(admin.getLoginName(), admin.getLoginName()));
        }
        //默认禁用
        admin.setStatus(StatusEnum.DISABLE);
        adminService.save(admin);
        adminService.saveUserRoles(admin.getId(), adminPARM.getRoleIds());
        return success(HttpStatus.CREATED);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody @Validated(AdminPARM.Update.class) AdminPARM adminPARM) {
        Admin admin = adminPARM.convert(Admin.class);
        admin.setId(id);
        adminService.updateById(admin);
        adminService.saveUserRoles(id, adminPARM.getRoleIds());
        return success();
    }

}

