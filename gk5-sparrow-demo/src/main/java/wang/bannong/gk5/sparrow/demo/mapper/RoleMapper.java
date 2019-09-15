package wang.bannong.gk5.sparrow.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import wang.bannong.gk5.sparrow.framework.mapper.BaseMapper;
import wang.bannong.gk5.sparrow.model.entity.Role;

/**
 * 角色表 Mapper 接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
