package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysMenu;
import wang.bannong.gk5.iam.dao.mapper.SysMenuMapper;

@Repository
public class SysMenuDao {
    @Autowired
    private SysMenuMapper masterSysMenuMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysMenuMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysMenu record) throws Exception {
        return masterSysMenuMapper.insert(record);
    }

    public int insertSelective(SysMenu record) throws Exception {
        return masterSysMenuMapper.insertSelective(record);
    }

    public SysMenu selectByPrimaryKey(Long id) throws Exception {
        return masterSysMenuMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysMenu record) throws Exception {
        return masterSysMenuMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysMenu record) throws Exception {
        return masterSysMenuMapper.updateByPrimaryKey(record);
    }

}
