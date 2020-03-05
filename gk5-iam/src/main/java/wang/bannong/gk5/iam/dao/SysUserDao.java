package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysUser;
import wang.bannong.gk5.iam.dao.mapper.SysUserMapper;

@Repository
public class SysUserDao {

    @Autowired
    private SysUserMapper masterSysUserMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysUserMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysUser record) throws Exception {
        return masterSysUserMapper.insert(record);
    }

    public int insertSelective(SysUser record) throws Exception {
        return masterSysUserMapper.insertSelective(record);
    }

    public SysUser selectByPrimaryKey(Long id) throws Exception {
        return masterSysUserMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUser record) throws Exception {
        return masterSysUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysUser record) throws Exception {
        return masterSysUserMapper.updateByPrimaryKey(record);
    }
}
