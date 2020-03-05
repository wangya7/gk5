package wang.bannong.gk5.iam.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.iam.common.dto.SysMenuDto;
import wang.bannong.gk5.iam.common.dto.SysOrgDto;
import wang.bannong.gk5.iam.common.dto.SysRoleDto;
import wang.bannong.gk5.iam.common.dto.SysTopicDto;
import wang.bannong.gk5.iam.common.dto.SysUserDto;
import wang.bannong.gk5.iam.service.IamService;
import wang.bannong.gk5.util.domain.FlexDataBus;

@Slf4j
@Service("iamService")
public class IamServiceDefault implements IamService {
    @Override
    public FlexDataBus accessTopic(Long sysUserId, String topicUnique) {
        return null;
    }

    @Override
    public FlexDataBus queryOrg(SysOrgDto dto) {
        return null;
    }

    @Override
    public FlexDataBus addOrg(SysOrgDto dto) {
        return null;
    }

    @Override
    public FlexDataBus modifyOrg(SysOrgDto dto) {
        return null;
    }

    @Override
    public FlexDataBus deleteOrg(SysOrgDto dto) {
        return null;
    }

    @Override
    public FlexDataBus queryMenu(SysMenuDto dto) {
        return null;
    }

    @Override
    public FlexDataBus addMenu(SysMenuDto dto) {
        return null;
    }

    @Override
    public FlexDataBus modifyMenu(SysMenuDto dto) {
        return null;
    }

    @Override
    public FlexDataBus deleteMenu(SysMenuDto dto) {
        return null;
    }

    @Override
    public FlexDataBus queryTopic(SysTopicDto dto) {
        return null;
    }

    @Override
    public FlexDataBus addTopic(SysTopicDto dto) {
        return null;
    }

    @Override
    public FlexDataBus modifyTopic(SysTopicDto dto) {
        return null;
    }

    @Override
    public FlexDataBus deleteTopic(SysTopicDto dto) {
        return null;
    }

    @Override
    public FlexDataBus queryRole(SysRoleDto dto) {
        return null;
    }

    @Override
    public FlexDataBus addRole(SysRoleDto dto) {
        return null;
    }

    @Override
    public FlexDataBus modifyRole(SysRoleDto dto) {
        return null;
    }

    @Override
    public FlexDataBus deleteRole(SysRoleDto dto) {
        return null;
    }

    @Override
    public FlexDataBus queryUser(SysUserDto dto) {
        return null;
    }

    @Override
    public FlexDataBus addUser(SysUserDto dto) {
        return null;
    }

    @Override
    public FlexDataBus modifyUser(SysUserDto dto) {
        return null;
    }

    @Override
    public FlexDataBus deleteUser(SysUserDto dto) {
        return null;
    }
}
