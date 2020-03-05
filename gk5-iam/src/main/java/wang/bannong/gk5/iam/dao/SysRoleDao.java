package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysRole;
import wang.bannong.gk5.iam.dao.mapper.SysRoleMapper;

@Repository
public class SysRoleDao {

    @Autowired
    private SysRoleMapper masterSysRoleMapper;
    
    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysRoleMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysRole record) throws Exception {
        return masterSysRoleMapper.insert(record);
    }

    public int insertSelective(SysRole record) throws Exception {
        return masterSysRoleMapper.insertSelective(record);
    }

    public SysRole selectByPrimaryKey(Long id) throws Exception {
        return masterSysRoleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysRole record) throws Exception {
        return masterSysRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysRole record) throws Exception {
        return masterSysRoleMapper.updateByPrimaryKey(record);
    }
}
