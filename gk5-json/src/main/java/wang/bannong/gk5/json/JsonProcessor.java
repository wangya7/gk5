package wang.bannong.gk5.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * JSON 接口定义
 * 声明直接参考
 * <p>
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2024/11/24
 */
public interface JsonProcessor {

    boolean isJson(String json);

    <T> T toJavaObject(String json, Type type);

    <T> List<T> toJavaList(String json, Class<T> clazz);

    String toJson(Object obj);

    List<?> getList(Map<String, ?> obj, String key);

    List<Map<String, ?>> getListOfObjects(Map<String, ?> obj, String key);

    List<String> getListOfStrings(Map<String, ?> obj, String key);

    Map<String, ?> getObject(Map<String, ?> obj, String key);

    Double getNumberAsDouble(Map<String, ?> obj, String key);

    Integer getNumberAsInteger(Map<String, ?> obj, String key);

    Long getNumberAsLong(Map<String, ?> obj, String key);

    String getString(Map<String, ?> obj, String key);

    List<Map<String, ?>> checkObjectList(List<?> rawList);

    List<String> checkStringList(List<?> rawList);
}
