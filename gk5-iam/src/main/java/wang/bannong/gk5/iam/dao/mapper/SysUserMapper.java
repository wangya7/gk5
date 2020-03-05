package wang.bannong.gk5.iam.dao.mapper;

import java.util.List;

import wang.bannong.gk5.iam.common.domain.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> selectByBo(SysUser record);
}