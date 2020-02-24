package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PageVo<T> implements Serializable {
	
	private static final long serialVersionUID = 7931389103372972873L;
	
	private Long total;
	private Integer rows;
	private List<T> list;
	
	public PageVo<T> empty(){
		return new PageVo<>(0,0L);
	}
	
	public PageVo(Integer rows,Long total){
		this.rows = rows;
		this.total = total;
	}
	
	public PageVo(Integer rows,Long total,List<T> list){
		this.list = list;
		this.rows = rows;
		this.total = total;
	}
}
