package wang.bannong.gk5.test.mybatisx.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 4170935004841605924L;

    private Long   id;
    private String name;
    private String role;
    private String passwd;
    private String permission;
    private Long   score;
}