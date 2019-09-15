package wang.bannong.gk5.gate.domain;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by wang.bannong on 2018/5/13 下午9:58
 */
@Data
public class GateInnerRequest {

    private GateRequest         gateRequest;
    private GateApiInfo         apiInfo;
    private UserAuthToken       userAuthToken = null;
    private Map<String, String> dataStore     = new HashMap<>();

    public static GateInnerRequest of(GateRequest request) {
        GateInnerRequest innerRequest = new GateInnerRequest();
        innerRequest.setGateRequest(request);
        Map<String, String> dataMap = request.getBizDataMap();
        if (dataMap != null && dataMap.size() > 0) {
            Map<String, String> bizData = new HashMap<>();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (StringUtils.isNotBlank(k) && StringUtils.isNotBlank(v)) {
                    bizData.put(k.trim(), v.trim());
                }
            }
            innerRequest.setDataStore(bizData);
        }

        return innerRequest;
    }

    public String get(String key) {
        Objects.requireNonNull(key, "key cannot be null");
        return !dataStore.isEmpty() ? dataStore.get(key) : null;
    }

    public long getUid() {
        return null != userAuthToken ? userAuthToken.getUid() : 0L;
    }

}
