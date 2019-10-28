package wang.bannong.gk5.sparrow.demo.model.parm;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.convert.Convert;

/**
 * 登陆参数
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PasswordPARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "原密码")
    @NotBlank(message = "原密码不能为空", groups = Update.class)
    private String oldPassword;
    @ApiModelProperty(notes = "新密码")
    @NotBlank(message = "新密码不能为空", groups = Update.class)
    private String newPassword;

    public interface Update {

    }
}
