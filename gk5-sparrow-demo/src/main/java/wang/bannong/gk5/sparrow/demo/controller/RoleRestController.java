package wang.bannong.gk5.sparrow.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import wang.bannong.gk5.sparrow.common.annotations.Resources;
import wang.bannong.gk5.sparrow.demo.model.parm.RolePARM;
import wang.bannong.gk5.sparrow.enums.AuthTypeEnum;
import wang.bannong.gk5.sparrow.framework.controller.SuperController;
import wang.bannong.gk5.sparrow.framework.responses.ApiResponses;
import wang.bannong.gk5.sparrow.model.dto.RoleDTO;
import wang.bannong.gk5.sparrow.model.entity.Role;
import wang.bannong.gk5.sparrow.service.IRoleMenuService;
import wang.bannong.gk5.sparrow.service.IRoleService;

/**
 * 角色表 前端控制器
 */
@Api(tags = {"Role"}, description = "角色相关接口")
@RestController
@RequestMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class RoleRestController extends SuperController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有角色(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "需要查询的角色名", paramType = "query")
    })
    @GetMapping
    public ApiResponses<IPage<RoleDTO>> page(@RequestParam(value = "roleName", required = false) String roleName) {
        return success(roleService.pageRoleDTO(this.<Role>getPage(), roleName));
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有角色")
    @GetMapping("/roles")
    public ApiResponses<List<Role>> list() {
        return success(roleService.list());
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    @GetMapping("/{id}")
    public ApiResponses<Role> get(@PathVariable("id") Integer id) {
        Role role = roleService.getById(id);
        return success(role);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "添加角色")
    @PostMapping
    public ApiResponses<Void> create(@RequestBody @Validated(RolePARM.Create.class) RolePARM rolePARM) {
        roleService.save(rolePARM.convert(Role.class));
        return success(HttpStatus.CREATED);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody @Validated(RolePARM.Update.class) RolePARM rolePARM) {
        Role role = rolePARM.convert(Role.class);
        role.setId(id);
        roleService.updateById(role);
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {
        roleService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "修改角色菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}/menus")
    public ApiResponses<Void> menus(@PathVariable("id") Integer id, @RequestBody @NotEmpty(message = "菜单ID不能为空") List<Integer> menuIds) {
        roleMenuService.saveRoleMenu(id, menuIds);
        return success();
    }
}

