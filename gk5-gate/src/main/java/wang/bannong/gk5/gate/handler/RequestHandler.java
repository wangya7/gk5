package wang.bannong.gk5.gate.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.bannong.gk5.gate.domain.Cookier;
import wang.bannong.gk5.gate.domain.GateConfigSetting;
import wang.bannong.gk5.gate.domain.ErrorMsgEnum;
import wang.bannong.gk5.gate.domain.GateResult;
import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.util.Constant;
import wang.bannong.gk5.util.MD5Util;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;

/**
 * Created by wang.bannong on 2018/5/13 下午8:47
 */
public class RequestHandler {

    private final static Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

    public static GateRequest convert2GateRequest(HttpServletRequest request) {
        Cookier cookier = Cookier.of(request);
        String api = format(request.getParameter("api"));
        String v = format(request.getParameter("v"));
        String ttid = format(request.getParameter("ttid"));
        String did = format(request.getParameter("channel"));
        String ts = format(request.getParameter("ts"));
        String sign = format(request.getParameter("sign"));
        String data = format(request.getParameter("data"));
        String authToken = format(request.getParameter("mat"));
        if (StringUtils.isBlank(authToken)) {
            // 从cookie中获取token
            authToken = StringUtils.trim(StringUtils.defaultIfBlank(cookier.getCookieValueByName("mat"), ""));
            LOG.info(">>>mat(in cookie)={}, api={}, ttid={} ", authToken, api, ttid);
        } else {
            LOG.info(">>>mat(in param)={}, api={}, ttid={}", authToken, api, ttid);
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


    final static String PARAMS_CANNOT_NULL = "api,v,ttid,did,ts,sign,method";
    final static long DIS_TS_THRESHOLD = 300;

    /**
     * request参数校验 不能返回null
     */
    public static GateResult checkReuqest(GateRequest request) {
        String[] arr = PARAMS_CANNOT_NULL.split(Constant.COMMA);
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
            LOG.error("exception detail", e);
            return GateResult.of(ErrorMsgEnum.api_param_missing);
        } catch (IllegalAccessException e) {
            LOG.error("exception detail", e);
            return GateResult.of(ErrorMsgEnum.api_param_missing);
        }


        // ts check
        long ts = Long.parseLong(request.getTs());
        long now = System.currentTimeMillis();
        if (ts > now || now - ts >= DIS_TS_THRESHOLD) {
            return GateResult.of(ErrorMsgEnum.api_param_missing);
        }

        return GateResult.SUCCESS;
    }

}
