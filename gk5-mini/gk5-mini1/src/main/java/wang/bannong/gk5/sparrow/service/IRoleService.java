package wang.bannong.gk5.sparrow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import wang.bannong.gk5.sparrow.model.dto.RoleDTO;
import wang.bannong.gk5.sparrow.model.entity.Role;
import wang.bannong.gk5.sparrow.framework.service.BaseService;

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
