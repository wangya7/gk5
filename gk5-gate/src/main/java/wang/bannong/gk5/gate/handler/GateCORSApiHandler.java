package wang.bannong.gk5.gate.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.bannong.gk5.gate.domain.GateConfigSetting;
import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.gate.util.URLUtils;

/**
 * Created by bn. on 2018/9/5 下午8:14
 */
public class GateCORSApiHandler {

    private static final Logger log = LoggerFactory.getLogger(GateCORSApiHandler.class);

    public static void setCORSSettingIfNeed(GateRequest mtopRequest, HttpServletRequest request, HttpServletResponse response) {
        if (GateConfigSetting.corsOriginDefaultSetting) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            return;
        }
        String host = request.getHeader("Host");
        String ua = request.getHeader("User-Agent");
        String refer = request.getHeader("Referer");
        String accessOriginUrl = URLUtils.getUrl(refer);
        int accessOriginUrlPort = URLUtils.getPort(refer);
        if (accessOriginUrlPort != 80) {
            accessOriginUrl += ":" + accessOriginUrlPort;
        }
        log.info(">>>cors,host=" + host + ",refer=" + refer + ",origin=" + accessOriginUrl + ",ua=" + ua);

        if (StringUtils.isBlank(accessOriginUrl)) {
            response.setHeader("Access-Control-Allow-Origin", getDefaultOriginHost());
        } else {
            if (contains(accessOriginUrl)) {
                response.setHeader("Access-Control-Allow-Origin", accessOriginUrl);
            }
        }
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
    }

    public static boolean contains(String accessOriginUrl) {
        if (StringUtils.isBlank(accessOriginUrl)) return false;
        String cro = GateConfigSetting.corsOriginHosts;
        if (cro.indexOf(accessOriginUrl) != -1) {
            return true;
        }
        return false;
    }

    private static String getDefaultOriginHost() {
        return GateConfigSetting.corsOriginHostsDef;
    }

}