package wang.bannong.gk5.iam.service;

import wang.bannong.gk5.iam.common.dto.SysMenuDto;
import wang.bannong.gk5.iam.common.dto.SysOrgDto;
import wang.bannong.gk5.iam.common.dto.SysRoleDto;
import wang.bannong.gk5.iam.common.dto.SysTopicDto;
import wang.bannong.gk5.iam.common.dto.SysUserDto;
import wang.bannong.gk5.util.domain.FlexDataBus;

public interface IamService {

    /**
     * 判断管理员是否可以访问该Topic
     *
     * @param sysUserId   管理员
     * @param topicUnique Topic标识
     * @return 结果
     */
    FlexDataBus accessTopic(Long sysUserId, String topicUnique);

    /***** 组织管理 *****/
    FlexDataBus queryOrg(SysOrgDto dto);
    FlexDataBus addOrg(SysOrgDto dto);
    FlexDataBus modifyOrg(SysOrgDto dto);
    FlexDataBus deleteOrg(SysOrgDto dto);

    /***** 菜单管理 *****/
    FlexDataBus queryMenu(SysMenuDto dto);
    FlexDataBus addMenu(SysMenuDto dto);
    FlexDataBus modifyMenu(SysMenuDto dto);
    FlexDataBus deleteMenu(SysMenuDto dto);

    /***** Topic管理 *****/
    FlexDataBus queryTopic(SysTopicDto dto);
    FlexDataBus addTopic(SysTopicDto dto);
    FlexDataBus modifyTopic(SysTopicDto dto);
    FlexDataBus deleteTopic(SysTopicDto dto);

    /***** 角色管理 *****/
    FlexDataBus queryRole(SysRoleDto dto);
    FlexDataBus addRole(SysRoleDto dto);
    FlexDataBus modifyRole(SysRoleDto dto);
    FlexDataBus deleteRole(SysRoleDto dto);

    /***** 人员管理 *****/
    FlexDataBus queryUser(SysUserDto dto);
    FlexDataBus addUser(SysUserDto dto);
    FlexDataBus modifyUser(SysUserDto dto);
    FlexDataBus deleteUser(SysUserDto dto);
}
