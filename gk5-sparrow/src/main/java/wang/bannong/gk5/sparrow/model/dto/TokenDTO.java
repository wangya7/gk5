package wang.bannong.gk5.sparrow.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.convert.Convert;

/**
 * <p>
 * TokenDTO
 * </p>
 *
 * @author Caratacus
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TokenDTO extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "账号id")
    private Integer uid;
    @ApiModelProperty(notes = "token")
    private String token;

}
