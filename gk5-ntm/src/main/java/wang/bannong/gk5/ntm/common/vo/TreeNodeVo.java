package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TreeNodeVo implements Serializable {
	
	private static final long serialVersionUID = 4028073896110453803L;
	
	private String id;
	
	private String text;
	
	private String state;//'open' 或 'closed'，默认：'open'
	
	private boolean checked;
	
	private String pid;
	
	private String url;
}
