package wang.bannong.gk5.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class AbstractJsonProcessor implements JsonProcessor {
    @Override
    public boolean isSupport() {
        return false;
    }

    @Override
    public boolean isJson(String json) {
        return false;
    }

    @Override
    public <T> T toJavaObject(String json, Type type) {
        return null;
    }

    @Override
    public <T> List<T> toJavaList(String json, Class<T> clazz) {
        return null;
    }

    @Override
    public String toJson(Object obj) {
        return null;
    }

    @Override
    public List<?> getList(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public List<Map<String, ?>> getListOfObjects(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public List<String> getListOfStrings(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public Map<String, ?> getObject(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public Double getNumberAsDouble(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public Integer getNumberAsInteger(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public Long getNumberAsLong(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public String getString(Map<String, ?> obj, String key) {
        return null;
    }

    @Override
    public List<Map<String, ?>> checkObjectList(List<?> rawList) {
        return null;
    }

    @Override
    public List<String> checkStringList(List<?> rawList) {
        return null;
    }
}
