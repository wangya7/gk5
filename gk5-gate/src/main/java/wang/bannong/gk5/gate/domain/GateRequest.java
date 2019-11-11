package wang.bannong.gk5.gate.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import wang.bannong.gk5.gate.handler.CookieHandler;
import wang.bannong.gk5.util.Constant;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang.bannong on 2018/5/13 下午8:33
 */
@Data
public class GateRequest implements Serializable {
    private static final long serialVersionUID = 4886431466723483411L;

    // URL协议参数
    private String method;
    private String ip;

    private String api;
    private String v;
    private String ttid;
    private String did;
    private String ts;
    private String mat;
    private String data;
    private String sign;
    private String oldValidMat;

    private transient Cookier cookier;

    public static GateRequest of(String method, String api, String v, String ttid, String mat, String did,
                                 String ts, String sign, String data) {
        GateRequest mtopRequest = new GateRequest();
        mtopRequest.method = method;
        mtopRequest.api = api;
        mtopRequest.v = v;
        mtopRequest.ttid = ttid;
        mtopRequest.mat = mat;
        mtopRequest.did = did;
        mtopRequest.ts = ts;
        mtopRequest.sign = sign;
        mtopRequest.data = data;
        return mtopRequest;
    }

    @JSONField(serialize=false)
    public Map<String, String> getBizDataMap() {
        if (StringUtils.isBlank(data))
            return new HashMap<>();
        String datas = null;
        try {
            datas = URLDecoder.decode(data, Constant.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(datas, new TypeReference<Map<String, String>>() {});
    }

    public String getBizData(String key) {
        Map<String, String> map = getBizDataMap();
        String obj = map.get(key);
        if (obj != null) {
            return obj;
        }
        return StringUtils.EMPTY;
    }

}
