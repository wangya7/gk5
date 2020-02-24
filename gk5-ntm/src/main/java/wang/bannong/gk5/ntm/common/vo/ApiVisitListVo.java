package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;

import lombok.Data;
import wang.bannong.gk5.ntm.common.model.NtmTraces;

/**
 * 接口访问列表
 *
 */
@Data
public class ApiVisitListVo extends NtmTraces implements Serializable {
	
	private static final long serialVersionUID = 7704040320753824020L;
	
	private String responseDataStr;
	
	private String requestDataStr;
	
}
