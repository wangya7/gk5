package wang.bannong.gk5.gate.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Page implements Serializable {
    private static final long serialVersionUID = 8845377365289895486L;

    private long                pageNum;
    private long                pages;
    private long                total;
    private long                pageSize;

    public Page() {
    }

    public Page(long pageNum, long pages, long pageSize, long total) {
        this.pageNum = pageNum;
        this.pages = pages;
        this.total = total;
        this.pageSize = pageSize;
    }

}