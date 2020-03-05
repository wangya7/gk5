package wang.bannong.gk5.iam.common.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 3451381915643654767L;

    private Long   id;
    private Long   pid;
    private String name;
    private Byte   type;
    private Byte   visible;
    private Byte   status;
    private Date   createTime;
}