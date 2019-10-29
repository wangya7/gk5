package wang.bannong.gk5.mini.sparrow.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.mini.sparrow.framework.model.BaseModel;

/**
 * 系统用户角色关系表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_admin_role")
public class AdminRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    public AdminRole(Integer uid, Integer roleId) {
        this.uid = uid;
        this.roleId = roleId;
    }

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 角色ID
     */
    private Integer roleId;

}