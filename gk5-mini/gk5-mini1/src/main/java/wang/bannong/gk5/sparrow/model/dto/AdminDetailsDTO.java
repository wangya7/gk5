package wang.bannong.gk5.sparrow.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.convert.Convert;

/**
 * 用户详情DTO
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminDetailsDTO extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "登陆名")
    private String loginName;

    @ApiModelProperty(notes = "昵称")
    private String nickname;

    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "手机")
    private String phone;

    @ApiModelProperty(notes = "权限路径")
    private List<String> perms;

}
