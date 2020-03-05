package wang.bannong.gk5.excel.style;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import wang.bannong.gk5.excel.ExcelConstant;
import wang.bannong.gk5.excel.ExcelCreator;

import java.util.List;
import java.util.Map;

/**
 * 普通的报表使用  ExcelStyle.single_sheet
 *
 * Created by wang.bannong on 2018/6/21 下午3:30
 */
public class ExcelCreatorSingleSheet implements ExcelCreator {
    /**
     * 创建excel文档
     *
     * @param data        数据
     * @param keys        list中map的key数组集合
     * @param columnNames excel的列名
     */
    @Override
    public Workbook createWorkBook(List<Map<String, Object>> data, String[] keys, String[] columnNames) {
        // 创建excel工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = workbook.createSheet(data.get(0).get(ExcelConstant.SHEET_NAME).toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < keys.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建第一种字体样式（用于列名）
        Font f1 = workbook.createFont();
        f1.setFontHeightInPoints((short) 12);
        f1.setColor(IndexedColors.BLACK.getIndex());
        f1.setBold(true);

        // 创建第二种字体样式（用于值）
        Font f2 = workbook.createFont();
        f2.setFontHeightInPoints((short) 12);
        f2.setColor(IndexedColors.BLACK.getIndex());
        f1.setBold(false);

        // 设置第一种单元格的样式（用于列名）
        CellStyle cs1 = workbook.createCellStyle();
        cs1.setFont(f1);
        cs1.setBorderLeft(BorderStyle.THIN);
        cs1.setBorderRight(BorderStyle.THIN);
        cs1.setBorderTop(BorderStyle.THIN);
        cs1.setBorderBottom(BorderStyle.THIN);
        cs1.setAlignment(HorizontalAlignment.CENTER);

        // 设置第二种单元格的样式（用于值）
        CellStyle cs2 = workbook.createCellStyle();
        cs2.setFont(f2);
        cs2.setBorderLeft(BorderStyle.THIN);
        cs2.setBorderRight(BorderStyle.THIN);
        cs2.setBorderTop(BorderStyle.THIN);
        cs2.setBorderBottom(BorderStyle.THIN);
        cs2.setAlignment(HorizontalAlignment.CENTER);

        // 设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs1);
        }
        // 设置每行每列的值
        for (int i = 1; i < data.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i);
            // 在row行上创建一个方格
            for (short j = 0; j < keys.length; j++) {
                Cell cell = row1.createCell(j);
                cell.setCellValue(data.get(i).get(keys[j]) == null ? " " : data.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return workbook;
    }
}
