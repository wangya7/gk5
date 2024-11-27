package wang.bannong.gk5.json.processor;

import java.util.Map;
import wang.bannong.gk5.json.exception.JsonParseException;

public abstract class AbstractJsonProcessor implements JsonProcessor {

    @Override
    public String mapToJson(Map<String, Object> map) {
        return toJson(map);
    }

    @Override
    public int getInt(String json, String key) {
        String str = get(json, key);
        try {
            return Integer.parseInt(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public Integer getInteger(String json, String key) {
        String str = get(json, key);
        try {
            return Integer.valueOf(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public long getLong(String json, String key) {
        String str = get(json, key);
        try {
            return Long.parseLong(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public Long getLongValue(String json, String key) {
        String str = get(json, key);
        try {
            return Long.valueOf(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public boolean getBoolean(String json, String key) {
        String str = get(json, key);
        try {
            return Boolean.parseBoolean(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public Boolean getBooleanValue(String json, String key) {
        String str = get(json, key);
        try {
            return Boolean.valueOf(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public byte getByte(String json, String key) {
        String str = get(json, key);
        try {
            return Byte.parseByte(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }

    @Override
    public Byte getByteValue(String json, String key) {
        String str = get(json, key);
        try {
            return Byte.valueOf(str);
        } catch (Throwable throwable) {
            throw new JsonParseException("Json[" + json + "] parse key[" + key + "]fail.");
        }
    }
}
