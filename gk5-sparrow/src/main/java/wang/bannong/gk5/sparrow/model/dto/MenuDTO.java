package wang.bannong.gk5.sparrow.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.enums.MenuTypeEnum;
import wang.bannong.gk5.sparrow.enums.StatusEnum;
import wang.bannong.gk5.sparrow.framework.model.BaseModel;

/**
 * 菜单表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MenuDTO extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "父菜单ID")
    private Integer parentId;

    @ApiModelProperty(notes = "菜单名称")
    private String menuName;

    @ApiModelProperty(notes = "路径")
    private String path;

    @ApiModelProperty(notes = "路由")
    private String router;

    @ApiModelProperty(notes = "类型:1:目录,2:菜单,3:按钮")
    private MenuTypeEnum menuType;

    @ApiModelProperty(notes = "菜单图标")
    private String icon;

    @ApiModelProperty(notes = "别名")
    private String alias;

    @ApiModelProperty(notes = "状态 0：禁用 1：正常")
    private StatusEnum status;

    @ApiModelProperty(notes = "关联资源ID")
    private List<String> resourceIds;

}
