package wang.bannong.gk5.excel;

import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wang.bannong on 2018/6/21 下午3:17
 */
public interface ExcelCreator {
    Workbook createWorkBook(List<Map<String, Object>> data, String[] keys, String[] columnNames);
}
