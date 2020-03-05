package wang.bannong.gk5.iam.dao.mapper;

import java.util.List;
import java.util.Set;

import wang.bannong.gk5.iam.common.domain.SysOrg;

public interface SysOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);

    List<SysOrg> selectByBo(SysOrg record);

    List<SysOrg> selectByIds(Set<Long> ids);
}