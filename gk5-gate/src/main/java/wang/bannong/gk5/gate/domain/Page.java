package wang.bannong.gk5.gate.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Page implements Serializable {
    private static final long serialVersionUID = 8845377365289895486L;

    private long currentPage;
    private long pages;
    private long totalCount;
    private long pageSize;

    public Page() {
    }

    public Page(long pageNum, long pages, long pageSize, long total) {
        this.currentPage = pageNum;
        this.pages = pages;
        this.totalCount = total;
        this.pageSize = pageSize;
    }

}