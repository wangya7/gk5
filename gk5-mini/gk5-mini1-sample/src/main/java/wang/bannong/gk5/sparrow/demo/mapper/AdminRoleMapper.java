package wang.bannong.gk5.sparrow.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import wang.bannong.gk5.sparrow.framework.mapper.BaseMapper;
import wang.bannong.gk5.sparrow.model.entity.AdminRole;

/**
 * 系统用户角色关系表 Mapper 接口
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

}
