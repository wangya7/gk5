package wang.bannong.gk5.mini.sparrow.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import wang.bannong.gk5.mini.sparrow.framework.mapper.BaseMapper;
import wang.bannong.gk5.mini.sparrow.model.entity.Admin;

/**
 * 系统用户表 Mapper 接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
