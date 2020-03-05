package wang.bannong.gk5.iam.dao.mapper;

import java.util.List;

import wang.bannong.gk5.iam.common.domain.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectByBo(SysRole record);
}