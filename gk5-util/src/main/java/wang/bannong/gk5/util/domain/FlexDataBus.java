package wang.bannong.gk5.util.domain;

import java.io.Serializable;
import java.util.*;

import lombok.Data;

/**
 * Created by wang.bannong on 2017/4/13.
 */
@Data
public class FlexDataBus implements Serializable {

    private static final long serialVersionUID = 3958384474944589195L;

    private static final String SINGLEKEY = "SINGLE_@_#_$_KEY";
    public static final FlexDataBus SUCCESS = succ();

    private Map<String, Object> bizMap = null;
    private boolean isSuccess = false;
    private String errMsg;
    private int errCode;
    private String type;
    private String namespace;

    public FlexDataBus() {
    }

    private static FlexDataBus succ() {
        FlexDataBus bus = new FlexDataBus();
        bus.bizMap = null;
        bus.isSuccess = true;
        return bus;
    }

    public static FlexDataBus succ(Object object) {
        FlexDataBus bus = new FlexDataBus();
        bus.bizMap = Collections.singletonMap(SINGLEKEY, object);
        bus.isSuccess = true;
        return bus;
    }

    public static FlexDataBus succ(Map<String, Object> bizMap) {
        FlexDataBus bus = new FlexDataBus();
        bus.bizMap = bizMap;
        bus.isSuccess = true;
        return bus;
    }

    public static FlexDataBus succ(Map<String, Object> bizMap, String type, String namespace) {
        FlexDataBus bus = new FlexDataBus();
        bus.bizMap = bizMap;
        bus.isSuccess = true;
        bus.type = type;
        bus.namespace = namespace;
        return bus;
    }


    public static FlexDataBus fail(int errCode, String errMsg) {
        FlexDataBus bus = new FlexDataBus();
        bus.isSuccess = false;
        bus.errMsg = errMsg;
        bus.errCode = errCode;
        return bus;
    }

    public static FlexDataBus fail(String errMsg) {
        FlexDataBus bus = new FlexDataBus();
        bus.isSuccess = false;
        bus.errMsg = errMsg;
        return bus;
    }

    public boolean contailsKey(String key) {
        Objects.requireNonNull(key);
        return null != bizMap.get(key);
    }

    public void put(String key, String value) {
        Objects.requireNonNull(key);
        bizMap.put(key, value);
    }

    public void put(String key, Object value) {
        Objects.requireNonNull(key);
        bizMap.put(key, value);
    }

    public void putAll(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null != entry.getKey()) {
                bizMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public <T> T get() {
        return get(SINGLEKEY);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) bizMap.get(key);
    }

    public String getString(String key) {
        return (String) bizMap.get(key);
    }

    public String getString(String key, String defaultValue) {
        String value = getString(key);
        return null != value ? value : defaultValue;
    }

    public Integer getInteger(String key) {
        return (Integer) bizMap.get(key);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        Integer value = getInteger(key);
        return value != null ? value : defaultValue;
    }

    public Long getLong(String key) {
        return (Long) bizMap.get(key);
    }

    public Long getLong(String key, Long defaultValue) {
        Long value = getLong(key);
        return value != null ? value : defaultValue;
    }

    public Double getDouble(String key) {
        return (Double) bizMap.get(key);
    }

    public Double getDouble(String key, Double defaultValue) {
        Double value = getDouble(key);
        return value != null ? value : defaultValue;
    }

    public List getListDefault(String key) {
        return (ArrayList) bizMap.get(key);
    }


    public void destroy() {
        if (bizMap != null) {
            bizMap.clear();
            bizMap = null;
        }
    }

    public void clear() {
        if (bizMap != null) {
            bizMap.clear();
        }
    }

}
