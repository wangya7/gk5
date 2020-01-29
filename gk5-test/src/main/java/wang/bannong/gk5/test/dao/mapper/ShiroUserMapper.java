package wang.bannong.gk5.test.dao.mapper;

import wang.bannong.gk5.test.common.ShiroUser;

public interface ShiroUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShiroUser record);

    int insertSelective(ShiroUser record);

    ShiroUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShiroUser record);

    int updateByPrimaryKey(ShiroUser record);

    ShiroUser queryByName(String name);
}