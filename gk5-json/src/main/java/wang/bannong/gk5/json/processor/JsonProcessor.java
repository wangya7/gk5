package wang.bannong.gk5.json.processor;

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

    /****************************************************/

    String mapToJson(Map<String, Object> map);

    /****************************************************/

    String get(String json, String key);

    String getJsonStr(String json, String key);

    int getInt(String json, String key);

    Integer getInteger(String json, String key);

    long getLong(String json, String key);

    Long getLongValue(String json, String key);

    boolean getBoolean(String json, String key);

    Boolean getBooleanValue(String json, String key);

    byte getByte(String json, String key);

    Byte getByteValue(String json, String key);

}
