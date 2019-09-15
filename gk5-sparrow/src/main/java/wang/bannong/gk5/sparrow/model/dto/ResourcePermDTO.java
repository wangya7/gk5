package wang.bannong.gk5.sparrow.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.sparrow.framework.model.convert.Convert;

/**
 * <p>
 * 权限 资源DTO
 * </p>
 *
 * @author Caratacus
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResourcePermDTO extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "请求方式")
    private String method;

    @ApiModelProperty(notes = "路径映射")
    private String mapping;

}
