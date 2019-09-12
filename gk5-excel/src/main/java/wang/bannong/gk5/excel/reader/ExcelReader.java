package wang.bannong.gk5.excel.reader;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import wang.bannong.gk5.excel.ExcelConstant;
import wang.bannong.gk5.excel.reader.annotation.ExcelProperties;

/**
 * 读取简单的EXCEL文件，EXCEL只有一行标题
 *
 * Created by bn. on 2019/5/17 9:38 AM
 */
public final class ExcelReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);

    public static Workbook getWorkbook(InputStream inputStream, String excelType) throws IOException {
        Workbook workbook = null;
        if (excelType.equalsIgnoreCase(ExcelConstant.XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (excelType.equalsIgnoreCase(ExcelConstant.XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            LOGGER.error("EXCEL格式出错, excelType={}", excelType);
            throw new RuntimeException("EXCEL格式出错");
        }
        return workbook;
    }


    public static <T> List<T> convert(InputStream inputStream, String excelType , Class clazz) throws IOException {
        Workbook workbook = null;
        try {
            workbook = getWorkbook(inputStream, excelType);

            List<T> resultDataList = new ArrayList<>();
            int length = workbook.getNumberOfSheets();
            for (int sheetNum = 0; sheetNum < length; sheetNum++) {
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                int firstRowNum = sheet.getFirstRowNum();
                Row firstRow = sheet.getRow(firstRowNum);
                if (null == firstRow) {
                    LOGGER.error("解析Excel失败，在第一行没有读取到任何数据！");
                }
                int rowStart = firstRowNum + 1;
                int rowEnd = sheet.getPhysicalNumberOfRows();
                for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (null == row) continue;
                    T t = convertRowToData(row, clazz);
                    if (t != null) {
                        resultDataList.add(t);
                    }
                }
            }

            return resultDataList;
        } catch (Exception e) {

           throw new RuntimeException(e);
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                LOGGER.error("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }


    /**
     * 将单元格内容转换为字符串
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }


    private static <T> T convertRowToData(Row row, Class clazz) {
        T model = null;
        try {
            model = (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            int modifiers = field.getModifiers();
            if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }

            ExcelProperties excelProperties = field.getDeclaredAnnotation(ExcelProperties.class);
            Class<?> fieldType = field.getType();
            int index = excelProperties.index();
            String cellValue = convertCellValueToString(row.getCell(index));
            if (StringUtils.isNotBlank(cellValue)) {
                try {
                    String fieldTypeName = fieldType.getSimpleName();
                    switch (fieldTypeName) {
                        case "String":
                            field.set(model, cellValue);
                            break;
                        case "int":
                            field.set(model, Integer.parseInt(cellValue));
                            break;
                        case "Integer":
                            field.set(model, Integer.valueOf(cellValue));
                            break;
                        case "byte":
                            field.set(model, Byte.parseByte(cellValue));
                            break;
                        case "Byte":
                            field.set(model, Byte.valueOf(cellValue));
                            break;
                        case "long":
                            field.set(model, Long.parseLong(cellValue));
                            break;
                        case "Long":
                            field.set(model, Long.valueOf(cellValue));
                            break;
                        case "double":
                            field.set(model, Double.parseDouble(cellValue));
                            break;
                        case "Double":
                            field.set(model, Double.valueOf(cellValue));
                            break;
                        case "float":
                            field.set(model, Float.parseFloat(cellValue));
                            break;
                        case "Float":
                            field.set(model, Float.valueOf(cellValue));
                            break;
                        case "boolean":
                            field.set(model, Boolean.parseBoolean(cellValue));
                            break;
                        case "Boolean":
                            field.set(model, Boolean.valueOf(cellValue));
                            break;
                        case "BigDecimal":
                            field.set(model, new BigDecimal(cellValue));
                            break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return model;
    }
}
