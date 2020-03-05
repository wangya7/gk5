package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import wang.bannong.gk5.iam.common.domain.SysOrg;
import wang.bannong.gk5.iam.dao.mapper.SysOrgMapper;

@Repository
public class SysOrgDao {

    @Autowired
    private SysOrgMapper masterSysOrgMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysOrgMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysOrg record) throws Exception {
        return masterSysOrgMapper.insert(record);
    }

    public int insertSelective(SysOrg record) throws Exception {
        return masterSysOrgMapper.insertSelective(record);
    }

    public SysOrg selectByPrimaryKey(Long id) throws Exception {
        return masterSysOrgMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysOrg record) throws Exception {
        return masterSysOrgMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysOrg record) throws Exception {
        return masterSysOrgMapper.updateByPrimaryKey(record);
    }

    public List<SysOrg> selectByBo(SysOrg record) throws Exception {
        return masterSysOrgMapper.selectByBo(record);
    }

    public List<SysOrg> selectByIds(Set<Long> ids) throws Exception {
        return masterSysOrgMapper.selectByIds(ids);
    }
}
