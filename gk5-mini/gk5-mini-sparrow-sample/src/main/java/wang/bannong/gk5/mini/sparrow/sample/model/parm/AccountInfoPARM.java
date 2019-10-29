package wang.bannong.gk5.mini.sparrow.sample.model.parm;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.mini.sparrow.framework.model.convert.Convert;

/**
 * 用户信息 PARM
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AccountInfoPARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "昵称")
    @NotBlank(groups = Update.class, message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "手机")
    private String phone;

    public interface Update {

    }

}
