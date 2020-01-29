package wang.bannong.gk5.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.test.common.ShiroUser;
import wang.bannong.gk5.test.dao.mapper.ShiroUserMapper;

@Repository
public class ShiroUserDao {

    @Autowired
    private ShiroUserMapper masterShiroUserMapper;
    @Autowired
    private ShiroUserMapper slaveShiroUserMapper;

    public int deleteByPrimaryKey(Integer id) {
        return masterShiroUserMapper.deleteByPrimaryKey(id);
    }

    public int insert(ShiroUser record) {
        return masterShiroUserMapper.insert(record);
    }

    public int insertSelective(ShiroUser record) {
        return masterShiroUserMapper.insertSelective(record);
    }

    public ShiroUser selectByPrimaryKey(Integer id) {
        return slaveShiroUserMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ShiroUser record) {
        return masterShiroUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ShiroUser record) {
        return masterShiroUserMapper.updateByPrimaryKey(record);
    }

    public ShiroUser queryByName(String username) {
        return slaveShiroUserMapper.queryByName(username);
    }
}
