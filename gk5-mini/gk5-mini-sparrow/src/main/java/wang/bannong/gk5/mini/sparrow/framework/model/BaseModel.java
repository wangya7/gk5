package wang.bannong.gk5.mini.sparrow.framework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wang.bannong.gk5.mini.sparrow.framework.model.convert.Convert;

/**
 * 自增主键父类
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseModel extends Convert {
    private static final long serialVersionUID = 1L;
    protected Integer id;

}
