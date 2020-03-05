package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysRoleTopic;
import wang.bannong.gk5.iam.dao.mapper.SysRoleTopicMapper;

@Repository
public class SysRoleTopicDao {

    @Autowired
    private SysRoleTopicMapper masterSysRoleTopicMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysRoleTopicMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysRoleTopic record) throws Exception {
        return masterSysRoleTopicMapper.insert(record);
    }

    public int insertSelective(SysRoleTopic record) throws Exception {
        return masterSysRoleTopicMapper.insertSelective(record);
    }

    public SysRoleTopic selectByPrimaryKey(Long id) throws Exception {
        return masterSysRoleTopicMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysRoleTopic record) throws Exception {
        return masterSysRoleTopicMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysRoleTopic record) throws Exception {
        return masterSysRoleTopicMapper.updateByPrimaryKey(record);
    }
}
