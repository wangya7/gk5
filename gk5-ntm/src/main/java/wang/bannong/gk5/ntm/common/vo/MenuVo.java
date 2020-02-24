package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuVo implements Serializable {
    private String id;

    private String pid;

    private String name;

    private String sn;

    private Byte status;

    private String createTime;

    private String modifyTime;
    
    private String url;
    
    private Integer sort;

    private static final long serialVersionUID = 1L;
}