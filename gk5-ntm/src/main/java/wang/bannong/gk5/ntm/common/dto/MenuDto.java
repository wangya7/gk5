package wang.bannong.gk5.ntm.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MenuDto implements Serializable {
    private Long id;

    private Long pid;

    private String name;

    private String sn;

    private Byte status;
    
    private String url;

    private Date createTime;

    private Date modifyTime;
    private Integer sort;
    
    private int page = 1;
    private int rows = 10;

    private static final long serialVersionUID = 1L;
}