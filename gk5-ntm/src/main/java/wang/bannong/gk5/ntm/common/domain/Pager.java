package wang.bannong.gk5.ntm.common.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Pager<E> implements Serializable {
    
	private static final long serialVersionUID = -5585357457875511522L;
	
	public Pager(List<E> rows, long total) {
		super();
		this.rows = rows;
		this.total = total;
	}
	
	public Pager() {
		super();
		this.total = 0L;
	}

	private List<E> rows;
    private Long total;
    
}
