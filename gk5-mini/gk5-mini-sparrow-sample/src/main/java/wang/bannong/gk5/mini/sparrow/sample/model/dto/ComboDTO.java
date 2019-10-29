package wang.bannong.gk5.mini.sparrow.sample.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.mini.sparrow.framework.model.BaseModel;

/**
 * 下拉框DTO
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ComboDTO extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "名称")
    private String name;

}
