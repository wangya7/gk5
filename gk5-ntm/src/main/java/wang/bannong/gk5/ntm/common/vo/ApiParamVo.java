package wang.bannong.gk5.ntm.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ApiParamVo{

    private String id;
    private String pid;
    private String apiId;
    private String name;
    private String status;
    private String type;
    private Boolean isRequired;
    private String errMsg;
    private String desp;
    private String example;
    private Date createTime;
    private Date modifyTime;

}
