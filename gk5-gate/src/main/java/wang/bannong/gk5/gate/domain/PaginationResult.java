package wang.bannong.gk5.gate.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Page page;
    private T list;
    private Map<String, Object> data = null;

    public static PaginationResult empty(long pageNum, long pageSize) {
        return new PaginationResult(pageNum, 0, 0, pageSize, Collections.emptyList());
    }

    public static PaginationResult empty(long pageNum, long pageSize, Map<String, Object> data) {
        return new PaginationResult().builder()
                .page(new Page(pageNum, 0, pageSize, 0))
                .data(data)
                .build();
    }

    public PaginationResult(long pageNum, long pages, long pageSize, long total, T list) {
        this.setPage(new Page(pageNum, pages, pageSize, total));
        this.list = list;
    }

    public PaginationResult(long pageNum, long pages, long pageSize, long total, T list, Map<String, Object> data) {
        this.setPage(new Page(pageNum, pages, pageSize, total));
        this.list = list;
        this.data = data;
    }

    public PaginationResult(Page page, T list) {
        this.page = page;
        this.list = list;
    }

}