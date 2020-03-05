package wang.bannong.gk5.iam.dao.mapper;

import java.util.List;

import wang.bannong.gk5.iam.common.domain.SysTopic;

public interface SysTopicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysTopic record);

    int insertSelective(SysTopic record);

    SysTopic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysTopic record);

    int updateByPrimaryKey(SysTopic record);

    List<SysTopic> selectByBo(SysTopic bo);
}