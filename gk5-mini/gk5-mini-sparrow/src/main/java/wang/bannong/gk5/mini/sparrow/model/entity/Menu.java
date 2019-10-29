package wang.bannong.gk5.mini.sparrow.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.mini.sparrow.framework.model.BaseModel;
import wang.bannong.gk5.mini.sparrow.enums.MenuTypeEnum;
import wang.bannong.gk5.mini.sparrow.enums.StatusEnum;

/**
 * 菜单表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class Menu extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 路由
     */
    private String router;

    /**
     * 类型:1:目录,2:菜单,3:按钮
     */
    private MenuTypeEnum menuType;

    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 别名
     */
    private String alias;
    /**
     * 状态 0：禁用 1：正常
     */
    private StatusEnum status;
    /**
     * 创建者ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createUid;

    /**
     * 修改者ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateUid;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
