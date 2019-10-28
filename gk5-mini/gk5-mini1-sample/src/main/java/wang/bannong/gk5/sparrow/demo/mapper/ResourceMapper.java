package wang.bannong.gk5.sparrow.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import wang.bannong.gk5.sparrow.framework.mapper.BaseMapper;
import wang.bannong.gk5.sparrow.model.dto.ResourcePermDTO;
import wang.bannong.gk5.sparrow.model.entity.Resource;

/**
 * 资源表 Mapper 接口
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 获取用户权限
     *
     * @param uid
     * @return
     */
    List<ResourcePermDTO> getUserResourcePerms(@Param("uid") Integer uid);

    /**
     * 获取用户菜单资源权限
     *
     * @param uid
     * @return
     */
    List<ResourcePermDTO> getUserMenuResourcePerms(@Param("uid") Integer uid);
}
