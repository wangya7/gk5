package wang.bannong.gk5.sparrow.demo.service.impl;

import org.springframework.stereotype.Service;

import wang.bannong.gk5.sparrow.demo.mapper.UserRoleMapper;
import wang.bannong.gk5.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.sparrow.model.entity.UserRole;
import wang.bannong.gk5.sparrow.service.IUserRoleService;

/**
 * 系统用户角色关系表 服务实现类
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
