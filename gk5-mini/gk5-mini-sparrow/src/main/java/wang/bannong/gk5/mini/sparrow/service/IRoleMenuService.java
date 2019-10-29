package wang.bannong.gk5.mini.sparrow.service;

import java.util.List;

import wang.bannong.gk5.mini.sparrow.framework.service.BaseService;
import wang.bannong.gk5.mini.sparrow.model.entity.RoleMenu;

/**
 * 角色菜单关系表 服务类
 */
public interface IRoleMenuService extends BaseService<RoleMenu> {

    /**
     * 保存角色菜单关系
     *
     * @param roleId
     * @param menuIds
     */
    void saveRoleMenu(Integer roleId, List<Integer> menuIds);
}
