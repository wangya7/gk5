package wang.bannong.gk5.mini.sparrow.sample.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import wang.bannong.gk5.mini.sparrow.enums.MenuTypeEnum;
import wang.bannong.gk5.mini.sparrow.enums.StatusEnum;
import wang.bannong.gk5.mini.sparrow.framework.mapper.BaseMapper;
import wang.bannong.gk5.mini.sparrow.model.dto.MenuTreeDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Menu;

/**
 * 菜单表 Mapper 接口
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取用户权限菜单
     *
     * @param uid
     * @param statusType
     * @param menuTypes
     * @return
     */
    List<MenuTreeDTO> getUserPermMenus(@Param("uid") Integer uid,
                                       @Param("statusType") StatusEnum statusType,
                                       @Param("menuTypes") List<MenuTypeEnum> menuTypes);
}
