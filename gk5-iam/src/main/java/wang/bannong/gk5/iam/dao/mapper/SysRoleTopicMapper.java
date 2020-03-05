package wang.bannong.gk5.iam.dao.mapper;

import wang.bannong.gk5.iam.common.domain.SysRoleTopic;

public interface SysRoleTopicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleTopic record);

    int insertSelective(SysRoleTopic record);

    SysRoleTopic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleTopic record);

    int updateByPrimaryKey(SysRoleTopic record);
}