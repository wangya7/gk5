package wang.bannong.gk5.ntm.common.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;
import wang.bannong.gk5.ntm.common.domain.Api;

/**
 * Created by bn. on 2019/7/4 3:03 PM
 */
@Getter
@ToString
public class NtmResponse implements Serializable {
    private static final long serialVersionUID = -803890451302063854L;
    private final int    code;
    private final String msg;
    private final Object data;
    private String       api;
    private int          v;
    private String       ia;

    NtmResponse(NtmResult ntmResult, Api api, NtmRequest request) {
        this.code = ntmResult.getCode();
        this.msg = ntmResult.getMsg();
        this.data = ntmResult.getData();
        if (api != null) {
            this.api = api.getUnique();
        }

        if (request != null) {
            this.v = api.getVersion();
            this.ia = request.getIa();
        }
    }


    public static Builder builder(NtmResult ntmResult) {
        return new Builder(ntmResult);
    }

    @ToString
    public static class Builder {
        private final NtmResult ntmResult;
        private Api             api;
        private NtmRequest      request;

        public Builder(NtmResult ntmResult) {
            this.ntmResult = ntmResult;
        }

        public Builder api(Api api) {
            this.api = api;
            return this;
        }

        public Builder request(NtmRequest request) {
            this.request = request;
            return this;
        }

        public NtmResponse builder() {
            return new NtmResponse(this.ntmResult, this.api, this.request);
        }
    }

}
