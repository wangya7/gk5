package wang.bannong.gk5.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wang.bannong on 2018/6/21 下午3:47
 */
public interface ExcelExecutor {

    default ExcelStyle excelStyle() {
        return ExcelStyle.single_sheet;
    }

    String[] excelColumnNames();

    String[] excelKeys();

    /**
     * 返回字节流更容易扩展，文档基本上需要上传到云端
     *
     * @param data 数据
     * @return 二进制字节
     */
    default byte[] exportExcel(List<Map<String, Object>> data) {
        byte[] bytes = null;
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            ExcelCreatorFactory.instance().getCreator(excelStyle()).createWorkBook(data, excelKeys(), excelColumnNames()).write(os);
            bytes =  os.toByteArray();
            os.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
}
