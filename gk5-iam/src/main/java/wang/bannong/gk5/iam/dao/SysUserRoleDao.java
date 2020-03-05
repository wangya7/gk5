package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysUserRole;
import wang.bannong.gk5.iam.dao.mapper.SysUserRoleMapper;

@Repository
public class SysUserRoleDao {
    @Autowired
    private SysUserRoleMapper masterSysUserRoleMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysUserRoleMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysUserRole record) throws Exception {
        return masterSysUserRoleMapper.insert(record);
    }

    public int insertSelective(SysUserRole record) throws Exception {
        return masterSysUserRoleMapper.insertSelective(record);
    }

    public SysUserRole selectByPrimaryKey(Long id) throws Exception {
        return masterSysUserRoleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUserRole record) throws Exception {
        return masterSysUserRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysUserRole record) throws Exception {
        return masterSysUserRoleMapper.updateByPrimaryKey(record);
    }
}
