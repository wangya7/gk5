package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * api访问统计
 */

@Data
public class ApiVisitVo extends ApiVo implements Serializable {
	
	private static final long serialVersionUID = 8476936377970646718L;
	
	
	private long   modelId;
    private String modelName;
    private long   modelTotal;
}
