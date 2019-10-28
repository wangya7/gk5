package wang.bannong.gk5.sparrow.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import wang.bannong.gk5.sparrow.model.dto.ResourcePermDTO;
import wang.bannong.gk5.sparrow.model.entity.Resource;
import wang.bannong.gk5.sparrow.enums.AuthTypeEnum;
import wang.bannong.gk5.sparrow.framework.service.BaseService;

/**
 * 资源表 服务类
 */
public interface IResourceService extends BaseService<Resource> {

    /**
     * 根据用户ID获取用户所有权限
     *
     * @param uid
     * @return
     */
    List<String> getUserPerms(Integer uid);

    /**
     * 获取开放权限资源列表
     *
     * @return
     */
    List<ResourcePermDTO> getOpenPerms();

    /**
     * 获取需要登录权限资源列表
     *
     * @return
     */
    List<ResourcePermDTO> getLoginPerms();

    /**
     * 获取指定类型权限资源列表
     *
     * @param authTypes 类型
     * @return
     */
    List<ResourcePermDTO> getPerms(AuthTypeEnum... authTypes);

    /**
     * 获取用户所有权限
     *
     * @param uid
     * @return
     */
    Set<ResourcePermDTO> getUserResourcePerms(Integer uid);

    /**
     * 获取所有权限
     *
     * @return
     */
    List<ResourcePermDTO> getPerms();

    /**
     * 获取某种请求方式资源权限
     *
     * @param method
     * @return
     */
    List<ResourcePermDTO> getResourcePerms(String method);

    /**
     * 获取资源权限标记
     *
     * @param method
     * @param mapping
     * @return
     */
    String getResourcePermTag(String method, String mapping);

    /**
     * 获取用户菜单资源权限
     *
     * @param uid
     * @return
     */
    List<ResourcePermDTO> getUserMenuResourcePerms(Integer uid);

    /**
     * <p>
     * 批量修改插入
     * </p>
     *
     * @param entityList 实体对象集合
     */
    boolean saveOrUpdateBatch(Collection<Resource> entityList);
}
