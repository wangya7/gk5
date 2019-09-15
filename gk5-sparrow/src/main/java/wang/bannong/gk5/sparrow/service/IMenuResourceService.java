package wang.bannong.gk5.sparrow.service;

import java.util.List;

import wang.bannong.gk5.sparrow.model.entity.MenuResource;
import wang.bannong.gk5.sparrow.framework.service.BaseService;

/**
 * 菜单资源关系表 服务类
 */
public interface IMenuResourceService extends BaseService<MenuResource> {

    /**
     * 根据菜单ID删除资源关系记录
     *
     * @param menuId
     */
    void removeByMenuId(Integer menuId);

    /**
     * 获取菜单资源关系
     *
     * @param menuId
     * @param resourceIds
     */
    List<MenuResource> getMenuResources(Integer menuId, List<String> resourceIds);

}
