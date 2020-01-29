package wang.bannong.gk5.test.dao.mapper;

import wang.bannong.gk5.test.common.ShiroRole;

public interface ShiroRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShiroRole record);

    int insertSelective(ShiroRole record);

    ShiroRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShiroRole record);

    int updateByPrimaryKey(ShiroRole record);

    ShiroRole selectByName(String role);
}