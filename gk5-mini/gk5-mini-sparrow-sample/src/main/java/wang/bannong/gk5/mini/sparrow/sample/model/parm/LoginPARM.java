package wang.bannong.gk5.mini.sparrow.sample.model.parm;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.mini.sparrow.framework.model.convert.Convert;

/**
 * 登陆参数
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginPARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "账号")
    @NotBlank(message = "用户名不能为空！")
    private String loginName;
    @ApiModelProperty(notes = "密码")
    @NotBlank(message = "密码不能为空！")
    private String password;

}
