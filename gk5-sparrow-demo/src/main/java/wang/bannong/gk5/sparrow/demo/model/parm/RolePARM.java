package wang.bannong.gk5.sparrow.demo.model.parm;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.convert.Convert;

/**
 * 角色表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolePARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "角色名称")
    @NotBlank(groups = {Create.class, Update.class}, message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(notes = "备注")
    private String remark;

    public interface Create {

    }

    public interface Update {

    }
}
