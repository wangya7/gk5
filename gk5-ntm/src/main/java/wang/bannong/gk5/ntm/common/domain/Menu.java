package wang.bannong.gk5.ntm.common.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 8345990251699869813L;

    private Long    id;
    private Long    pid;
    private String  name;
    private String  sn;
    private String  url;
    private Byte    status;
    private Integer sort;
    private Date    createTime;
    private Date    modifyTime;

}