package wang.bannong.gk5.ntm.common.model;

import java.io.Serializable;
import java.util.Collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bn. on 2019/10/11 2:40 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResult<T> implements Serializable {
    private static final long serialVersionUID = -5926063110399372045L;
    private int    pageNum;
    private int    pageSize;
    private long   total;
    private int    pages;
    private T      list;
    private Object extend;

    public static PaginationResult empty(int pageNum, int pageSize) {
        return of(pageNum, pageSize, 0, 0, Collections.emptyList(), null);
    }

    public static <T> PaginationResult of(int pageNum, int pageSize, int pages, long total, T list) {
        return of(pageNum, pageSize, pages, total, list, null);
    }

    public static <T> PaginationResult of(int pageNum, int pageSize, int pages, long total, T list, Object extend) {
        PaginationResult result = new PaginationResult();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setPages(pages);
        result.setTotal(total);
        result.setList(list);
        result.setExtend(extend);
        return result;
    }

}
