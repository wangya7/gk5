package wang.bannong.gk5.sparrow.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.BaseModel;

/**
 * 系统用户角色关系表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
public class UserRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    public UserRole(Integer uid, Integer roleId) {
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
