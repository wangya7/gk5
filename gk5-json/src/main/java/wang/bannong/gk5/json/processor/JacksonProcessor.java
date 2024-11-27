package wang.bannong.gk5.json.processor;

import java.lang.reflect.Type;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import wang.bannong.gk5.json.exception.IllegalJsonException;

/**
 * Jackson implements Json.
 * <p>
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2024/11/24
 */
public class JacksonProcessor extends AbstractJsonProcessor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private volatile Object jacksonCache = null;

    @Override
    public boolean isJson(String json) {
        try {
            JsonNode node = objectMapper.readTree(json);
            return node.isObject() || node.isArray();
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    @Override
    public <T> T toJavaObject(String json, Type type) {
        try {
            return getJackson().readValue(json, getJackson().getTypeFactory().constructType(type));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public <T> List<T> toJavaList(String json, Class<T> clazz) {
        try {
            return getJackson()
                    .readValue(json, getJackson().getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toJson(Object obj) {
        try {
            return getJackson().writeValueAsString(obj);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private JsonMapper getJackson() {
        if (jacksonCache == null || !(jacksonCache instanceof JsonMapper)) {
            synchronized (this) {
                if (jacksonCache == null || !(jacksonCache instanceof JsonMapper)) {
                    jacksonCache = JsonMapper.builder()
                                             .configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true)
                                             .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                             .serializationInclusion(JsonInclude.Include.NON_NULL)
                                             .addModule(new JavaTimeModule())
                                             .build();
                }
            }
        }
        return (JsonMapper) jacksonCache;
    }

    public JsonNode getJson(String json, String key) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (Throwable e) {
            throw new IllegalJsonException("illegal json[" + json + "]");
        }
        if (null == jsonNode) {
            throw new IllegalJsonException("json is null");
        }
        return jsonNode.get(key);
    }


    @Override
    public String get(String json, String key) {
        JsonNode jsonNode = getJson(json, key);
        return jsonNode != null ? jsonNode.asText() : null;
    }

    @Override
    public String getJsonStr(String json, String key) {
        JsonNode jsonNode = getJson(json, key);
        return jsonNode != null ? jsonNode.toString(): null;
    }
}
