package wang.bannong.gk5.sparrow.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.BaseModel;

/**
 * 菜单资源关系表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu_resource")
public class MenuResource extends BaseModel {

    private static final long serialVersionUID = 1L;

    public MenuResource(Integer menuId, String resourceId) {
        this.menuId = menuId;
        this.resourceId = resourceId;
    }

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 资源ID
     */
    private String resourceId;


}
