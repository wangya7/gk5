package wang.bannong.gk5.iam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wang.bannong.gk5.iam.common.domain.SysTopic;
import wang.bannong.gk5.iam.dao.mapper.SysTopicMapper;

@Repository
public class SysTopicDao {

    @Autowired
    private SysTopicMapper masterSysTopicMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return masterSysTopicMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysTopic record) throws Exception {
        return masterSysTopicMapper.insert(record);
    }

    public int insertSelective(SysTopic record) throws Exception {
        return masterSysTopicMapper.insertSelective(record);
    }

    public SysTopic selectByPrimaryKey(Long id) throws Exception {
        return masterSysTopicMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysTopic record) throws Exception {
        return masterSysTopicMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysTopic record) throws Exception {
        return masterSysTopicMapper.updateByPrimaryKey(record);
    }
}
