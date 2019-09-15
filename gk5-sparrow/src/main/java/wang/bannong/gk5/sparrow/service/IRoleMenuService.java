package wang.bannong.gk5.sparrow.service;

import java.util.List;

import wang.bannong.gk5.sparrow.model.entity.RoleMenu;
import wang.bannong.gk5.sparrow.framework.service.BaseService;

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
