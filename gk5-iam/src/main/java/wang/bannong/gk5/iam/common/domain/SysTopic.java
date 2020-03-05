package wang.bannong.gk5.iam.common.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysTopic implements Serializable {
    private static final long serialVersionUID = 7691733475486996663L;

    private Long   id;
    private String name;
    private String unique;
    private Long   menu2Id;
    private Long   menu3Id;
    private Byte   status;
    private Date   createTime;
    private Date   modifyTime;

}