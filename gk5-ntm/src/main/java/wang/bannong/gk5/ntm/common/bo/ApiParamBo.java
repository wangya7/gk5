package wang.bannong.gk5.ntm.common.bo;

import java.util.Date;

import lombok.Data;
import wang.bannong.gk5.ntm.common.domain.ApiParam;

@Data
public class ApiParamBo extends ApiParam {
    private static final long serialVersionUID = -4316872830113380619L;
    private Long   notId; // 查询ID不等于这个的
    private String likeName;
    private Date   beginTime;
    private Date   endTime;
}
