package wang.bannong.gk5.ntm.common.model;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import wang.bannong.gk5.ntm.common.domain.Api;

public class NtmInnerRequest {

    private NtmRequest          request;
    private AuthToken           authToken;
    private long                entityId = 0;
    private Api                 api;
    private Map<String, String> dataStore = new HashMap<>();

    public static NtmInnerRequest of(NtmRequest request) {
        NtmInnerRequest innerRequest = new NtmInnerRequest();
        innerRequest.setRequest(request);

        Map<String, String> dataMap = request.getData();
        if (MapUtils.isNotEmpty(dataMap)) {
            Map<String, String> dataStore = new HashMap<>();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                String key, value;
                if (StringUtils.isNotBlank(key = entry.getKey()) && StringUtils.isNotBlank(value = entry.getValue())) {
                    dataStore.put(key, value);
                }
            }
            innerRequest.setDataStore(dataStore);
        }

        return innerRequest;
    }

    public NtmRequest getRequest() {
        return request;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public long getEntityId() {
        return entityId;
    }

    public Api getApi() {
        return api;
    }

    public String get(String key) {
        return dataStore.get(Objects.requireNonNull(key, "inner-request key cannot be null"));
    }

    public Map<String, String> getDataStore() {
        return dataStore;
    }

    private void setRequest(NtmRequest request) {
        this.request = request;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
        if (authToken != null) {
            this.entityId = authToken.getEntityId();
        }
    }

    public void setApi(Api api) {
        this.api = api;
    }

    private void setDataStore(Map<String, String> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public String toString() {
        return "NtmInnerRequest{" +
                "request=" + request +
                ", authToken=" + authToken +
                ", entityId=" + entityId +
                ", api=" + api +
                ", dataStore=" + dataStore +
                '}';
    }
}