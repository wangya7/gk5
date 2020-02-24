package wang.bannong.gk5.ntm.common.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录/注销 模型
 * Created by bn. on 2019/10/17 12:38 PM
 */
@Data
public class Entity implements Serializable {
    private static final long serialVersionUID = 4297631829121850860L;
    private Long   id;
    private String mobile;
    private String name;
}
