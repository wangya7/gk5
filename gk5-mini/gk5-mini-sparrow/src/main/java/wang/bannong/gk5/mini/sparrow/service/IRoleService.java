package wang.bannong.gk5.mini.sparrow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import wang.bannong.gk5.mini.sparrow.framework.service.BaseService;
import wang.bannong.gk5.mini.sparrow.model.dto.RoleDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Role;

/**
 * 角色表 服务类
 */
public interface IRoleService extends BaseService<Role> {

    /**
     * 获取角色详情列表
     *
     * @param page
     * @param roleName
     * @return
     */
    IPage<RoleDTO> pageRoleDTO(Page<Role> page, String roleName);
}
