package wang.bannong.gk5.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * JSON Tools.
 * <p>
 *
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2024/11/24
 */
public final class Json {

    private Json() {
    }

    static JsonProcessor processor;

    static {
        processor = JsonProcessorStrategy.processor();
    }

    public static boolean isJson(String json) {
        return processor.isJson(json);
    }

    public static <T> T toJavaObject(String json, Type type) {
        return processor.toJavaObject(json, type);
    }

    public static <T> List<T> toJavaList(String json, Class<T> clazz) {
        return processor.toJavaList(json, clazz);
    }

    public static String toJson(Object obj) {
        return processor.toJson(obj);
    }

    public static List<?> getList(Map<String, ?> obj, String key) {
        return processor.getList(obj, key);
    }

    public static List<Map<String, ?>> getListOfObjects(Map<String, ?> obj, String key) {
        return processor.getListOfObjects(obj, key);
    }

    public static List<String> getListOfStrings(Map<String, ?> obj, String key) {
        return processor.getListOfStrings(obj, key);
    }

    public static Map<String, ?> getObject(Map<String, ?> obj, String key) {
        return processor.getObject(obj, key);
    }

    public static Double getNumberAsDouble(Map<String, ?> obj, String key) {
        return processor.getNumberAsDouble(obj, key);
    }

    public static Integer getNumberAsInteger(Map<String, ?> obj, String key) {
        return processor.getNumberAsInteger(obj, key);
    }

    public static Long getNumberAsLong(Map<String, ?> obj, String key) {
        return processor.getNumberAsLong(obj, key);
    }

    public static String getString(Map<String, ?> obj, String key) {
        return processor.getString(obj, key);
    }

    public static List<Map<String, ?>> checkObjectList(List<?> rawList) {
        return processor.checkObjectList(rawList);
    }

    public static List<String> checkStringList(List<?> rawList) {
        return processor.checkStringList(rawList);
    }
}
