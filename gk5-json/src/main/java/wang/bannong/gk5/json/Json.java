package wang.bannong.gk5.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import wang.bannong.gk5.json.processor.JsonProcessor;
import wang.bannong.gk5.json.processor.JsonProcessorStrategy;

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

    /****************************************************/

    public static String mapToJson(Map<String, Object> map) {
        return processor.mapToJson(map);
    }

    /****************************************************/

    public static String get(String json, String key) {
        return processor.get(json, key);
    }

    public static String getJsonStr(String json, String key) {
        return processor.getJsonStr(json, key);
    }

    public static int getInt(String json, String key) {
        return processor.getInt(json, key);
    }

    public static Integer getInteger(String json, String key) {
        return processor.getInteger(json, key);
    }

    public static long getLong(String json, String key) {
        return processor.getLong(json, key);
    }

    public static Long getLongValue(String json, String key) {
        return processor.getLongValue(json, key);
    }

    public static boolean getBoolean(String json, String key) {
        return processor.getBoolean(json, key);
    }

    public static Boolean getBooleanValue(String json, String key) {
        return processor.getBooleanValue(json, key);

    }

    public static byte getByte(String json, String key) {
        return processor.getByte(json, key);
    }

    public static Byte getByteValue(String json, String key) {
        return processor.getByteValue(json, key);
    }

}
