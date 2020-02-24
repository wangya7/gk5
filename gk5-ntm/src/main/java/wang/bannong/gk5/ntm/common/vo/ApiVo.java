package wang.bannong.gk5.ntm.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.Data;

@Data
public class ApiVo{

    private String  id;
    private String  unique;
    private String  name;
    private Integer version;
    private Byte    method;
    private String  appid;
    private String  dto;
    private String  service;
    private Boolean isIa;
    private Integer dailyFlowLimit;
    private String  innerInterface;
    private String  innerMethod;
    private Boolean isAsync;
    private Byte    status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date    createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date    modifyTime;

    private static final long serialVersionUID = 3366245839146063293L;

}
