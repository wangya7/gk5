package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysRoleMenu;
import wang.bannong.gk5.iam.dao.mapper.SysRoleMenuMapper;

@Repository
public class SysRoleMenuDao {

    @Autowired
    private SysRoleMenuMapper masterSysRoleMenuMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysRoleMenuMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysRoleMenu record) throws Exception {
        return masterSysRoleMenuMapper.insert(record);
    }

    public int insertSelective(SysRoleMenu record) throws Exception {
        return masterSysRoleMenuMapper.insertSelective(record);
    }

    public SysRoleMenu selectByPrimaryKey(Long id) throws Exception {
        return masterSysRoleMenuMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysRoleMenu record) throws Exception {
        return masterSysRoleMenuMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysRoleMenu record) throws Exception {
        return masterSysRoleMenuMapper.updateByPrimaryKey(record);
    }
}
