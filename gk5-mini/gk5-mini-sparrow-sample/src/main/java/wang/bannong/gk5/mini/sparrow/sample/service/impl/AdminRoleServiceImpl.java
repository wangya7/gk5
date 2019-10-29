package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import org.springframework.stereotype.Service;

import wang.bannong.gk5.mini.sparrow.sample.mapper.AdminRoleMapper;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.model.entity.AdminRole;
import wang.bannong.gk5.mini.sparrow.service.IAdminRoleService;

/**
 * 系统用户角色关系表 服务实现类
 */
@Service
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

}
