package wang.bannong.gk5.gate.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.gate.config.GateConfig;
import wang.bannong.gk5.gate.domain.Cookier;
import wang.bannong.gk5.gate.domain.ErrorMsgEnum;
import wang.bannong.gk5.gate.domain.GateResult;
import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.util.Constant;
import wang.bannong.gk5.util.RSAUtils;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;

/**
 * Created by wang.bannong on 2018/5/13 下午8:47
 */
@Slf4j
public class RequestHandler {

    public static GateRequest convert2GateRequest(HttpServletRequest request) {
        Cookier cookier = Cookier.of(request);
        String api = format(request.getParameter(GateConfig.api));
        String v = format(request.getParameter(GateConfig.v));
        String ttid = format(request.getParameter(GateConfig.ttid));
        String did = format(request.getParameter(GateConfig.did));
        String ts = format(request.getParameter(GateConfig.ts));
        String sign = format(request.getParameter(GateConfig.sign));
        String data = format(request.getParameter(GateConfig.data));
        String authToken = format(request.getParameter(GateConfig.mat));
        if (StringUtils.isBlank(authToken)) {
            // 从cookie中获取token
            authToken = StringUtils.trim(StringUtils.defaultIfBlank(cookier.getCookieValueByName(GateConfig.mat), ""));
            log.info("[GATE] mat(in cookie)={}, api={}, ttid={} ", authToken, api, ttid);
        } else {
            log.info("[GATE] mat(in param)={}, api={}, ttid={}", authToken, api, ttid);
        }

        GateRequest mtopRequest = GateRequest.of(request.getMethod().toLowerCase(), api, v, ttid, authToken, did, ts, sign, data);
        mtopRequest.setIp(getIp(request));
        mtopRequest.setCookier(cookier);
        return mtopRequest;
    }

    private static String getIp(HttpServletRequest request) {
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String format(String value) {
        return StringUtils.trim(StringUtils.defaultIfBlank(value, ""));
    }


    /**
     * request参数校验 不能返回null
     */
    public static GateResult checkReuqest(GateRequest request) {
        String[] arr = GateConfig.API_PARAMS_CANNOT_NULL.split(Constant.COMMA);
        try {
            for (String a : arr) {
                Field field = request.getClass().getDeclaredField(a);
                field.setAccessible(true);
                String v = (String) field.get(request);
                if (StringUtils.isBlank(v)) {
                    return GateResult.of(ErrorMsgEnum.api_param_missing, a);
                }
            }
        } catch (NoSuchFieldException e) {
            log.error("exception detail", e);
            return GateResult.of(ErrorMsgEnum.api_param_missing);
        } catch (IllegalAccessException e) {
            log.error("exception detail", e);
            return GateResult.of(ErrorMsgEnum.api_param_missing);
        }

        // sign check
        String sign = request.getSign();
        if (GateConfig.sign4Dev.equalsIgnoreCase(sign)) {
            return GateResult.SUCCESS;
        }
        String signArgs = String.format(GateConfig.SIGN_FORMAT, request.getApi(), request.getData(),
                request.getDid(), request.getMat(), request.getTs(), request.getTtid(), request.getV());
        log.info("[GATE] sign 加密的text={}", signArgs);
        String text = RSAUtils.decrypt(RSAUtils.base64Decode(sign), RSAUtils.getPrivateKey(GateConfig.privateKey));
        log.info("[GATE] sign 解密的text={}", text);
        if (!text.equals(signArgs)) {
            return GateResult.of(ErrorMsgEnum.sign_error);
        }

        // ts check
        long ts = Long.parseLong(request.getTs());
        long now = System.currentTimeMillis();
        log.info("[GATE] 校验时间戳，当前时间【{}】，客户端时间【{}】", now, ts);
        if (now - ts > GateConfig.DIS_TS_THRESHOLD) {
            return GateResult.of(ErrorMsgEnum.api_timeout);
        }

        return GateResult.SUCCESS;
    }

}
