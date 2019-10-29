package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.bannong.gk5.mini.sparrow.common.utils.TypeUtils;
import wang.bannong.gk5.mini.sparrow.sample.mapper.RoleMapper;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.model.dto.RoleDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Role;
import wang.bannong.gk5.mini.sparrow.model.entity.RoleMenu;
import wang.bannong.gk5.mini.sparrow.service.IRoleMenuService;
import wang.bannong.gk5.mini.sparrow.service.IRoleService;

/**
 * 角色表 服务实现类
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public IPage<RoleDTO> pageRoleDTO(Page<Role> page, String roleName) {
        IPage<Role> rolePage = query().like(StringUtils.isNotEmpty(roleName), Role::getRoleName, roleName).page(page);
        return rolePage.convert(role -> {
            RoleDTO roleDTO = role.convert(RoleDTO.class);
            roleDTO.setMenuIds(roleMenuService.query()
                    .select(RoleMenu::getMenuId)
                    .eq(RoleMenu::getRoleId, role.getId())
                    .listObjs(TypeUtils::castToInt)
            );
            return roleDTO;
        });
    }
}
