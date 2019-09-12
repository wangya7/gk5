package wang.bannong.gk5.excel;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by wang.bannong on 2018/6/21 下午4:03
 */
public class ExcelExecutorTest implements ExcelExecutor {

    @Override
    public String[] excelColumnNames() {
        return new String[]{
                "姓名",
                "数学",
                "英语",
                "语文",
                "计算机"
        };
    }

    @Override
    public String[] excelKeys() {
        return new String[]{
                "name",
                "maths",
                "english",
                "chinese",
                "information"
        };
    }


    public static void main(String... args) throws IOException {
        ExcelExecutor executor = new ExcelExecutorTest();
        List<Map<String, Object>> data = new ArrayList<>();
        data.add(Collections.singletonMap(ExcelConstant.SHEET_NAME, "学生成绩列表"));

        Random random = new Random();

        List<String> names = new ArrayList<>(Arrays.asList("张无忌", "赵敏", "周芷若", "小昭", "谢逊", "陈坤", "韦一笑"));
        for (String name : names) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("maths", random.nextInt(20) + 80);
            map.put("english", random.nextInt(20) + 58);
            map.put("chinese", random.nextInt(20) + 70);
            map.put("information", random.nextInt(20) + 75);
            data.add(map);
        }

        byte[] bytes = executor.exportExcel(data);
        // init
        String filePath = "/Users/wangya/Downloads/aaaaaa.xls";
        File file = new File(filePath);
        Files.write(bytes, file);
    }
}
