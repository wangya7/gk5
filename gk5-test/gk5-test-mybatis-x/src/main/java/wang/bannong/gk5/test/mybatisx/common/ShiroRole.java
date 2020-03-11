package wang.bannong.gk5.test.mybatisx.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShiroRole implements Serializable {
    private static final long serialVersionUID = 7537265358660007097L;

    private Integer id;
    private String  role;
    private String  permission;
}