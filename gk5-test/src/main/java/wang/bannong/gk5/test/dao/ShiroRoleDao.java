package wang.bannong.gk5.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.test.common.ShiroRole;
import wang.bannong.gk5.test.dao.mapper.ShiroRoleMapper;

@Repository
public class ShiroRoleDao {

    @Autowired
    private ShiroRoleMapper masterShiroRoleMapper;
    @Autowired
    private ShiroRoleMapper slaveShiroRoleMapper;

    public int deleteByPrimaryKey(Integer id)  {
        return masterShiroRoleMapper.deleteByPrimaryKey(id);
    }

    public int insert(ShiroRole record)  {
        return masterShiroRoleMapper.insert(record);
    }

    public int insertSelective(ShiroRole record)  {
        return masterShiroRoleMapper.insertSelective(record);
    }

    public ShiroRole selectByPrimaryKey(Integer id)  {
        return slaveShiroRoleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ShiroRole record)  {
        return masterShiroRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ShiroRole record)  {
        return masterShiroRoleMapper.updateByPrimaryKey(record);
    }
}
