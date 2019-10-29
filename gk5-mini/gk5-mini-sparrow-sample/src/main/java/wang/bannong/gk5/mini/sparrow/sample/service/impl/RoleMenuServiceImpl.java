package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import wang.bannong.gk5.mini.sparrow.sample.mapper.RoleMenuMapper;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.model.entity.RoleMenu;
import wang.bannong.gk5.mini.sparrow.service.IRoleMenuService;

/**
 * <p>
 * 角色菜单关系表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    @Transactional
    public void saveRoleMenu(Integer roleId, List<Integer> menuIds) {
        delete().eq(RoleMenu::getRoleId, roleId).execute();
        saveBatch(menuIds.stream().map(menuId -> new RoleMenu(roleId, menuId)).collect(Collectors.toList()));
    }
}
