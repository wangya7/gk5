package wang.bannong.gk5.sparrow.demo.service.impl;

import org.springframework.stereotype.Service;

import wang.bannong.gk5.sparrow.demo.mapper.AdminRoleMapper;
import wang.bannong.gk5.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.sparrow.model.entity.AdminRole;
import wang.bannong.gk5.sparrow.service.IAdminRoleService;

/**
 * 系统用户角色关系表 服务实现类
 */
@Service
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

}
