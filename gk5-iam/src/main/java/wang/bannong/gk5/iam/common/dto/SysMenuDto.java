package wang.bannong.gk5.iam.common.dto;

import lombok.Data;

@Data
public class SysMenuDto extends SysBaseDto {
    private static final long serialVersionUID = 4263245684749115165L;

    private Long   pid;
    private String name;
    private Byte   type;
    private Byte   visible;
    private Byte   status;

}
