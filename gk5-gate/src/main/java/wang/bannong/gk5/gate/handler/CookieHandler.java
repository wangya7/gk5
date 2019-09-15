package wang.bannong.gk5.gate.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import wang.bannong.gk5.gate.domain.Cookier;
import wang.bannong.gk5.gate.domain.GateConfigSetting;
import wang.bannong.gk5.gate.domain.GateInnerRequest;
import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.gate.domain.GateResponse;

/**
 * Created by wang.bannong on 2018/5/13 下午8:44
 */
public final class CookieHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieHandler.class);

    public static void setCookieSettingIfNeed(GateInnerRequest innerRequest, GateRequest gateRequest,
                                              GateResponse gateResponse, HttpServletResponse response) {
        Cookier cookier = gateRequest.getCookier();

        int timeSeconds = getCookieExpireTime(innerRequest);

        String updateMat = gateResponse.getMeta().getMat();
        String domain = GateConfigSetting.domains;

        if (isLogoutApi(gateRequest.getApi())) {
            // 退出登录时删除cookie值
            cookier.delCookie(response, GateConfigSetting.mat);
        } else {
            boolean isWriteCookieAlways = GateConfigSetting.isWriteCookieAlways;
            if (StringUtils.isNotBlank(updateMat)) {
                cookier.setCookie(response, GateConfigSetting.mat, updateMat, timeSeconds, domain);
                LOGGER.info(">>>setcookie,updateMat=" + updateMat + ",domain=" + domain + ",api=" + gateRequest.getApi());
            } else if (isWriteCookieAlways) {
                String oldMat = gateRequest.getOldValidMat();
                if (StringUtils.isNotBlank(oldMat)) {
                    cookier.setCookie(response, GateConfigSetting.mat, oldMat, timeSeconds, domain);
                    LOGGER.info(">>>setcookie, oldMat=" + oldMat + ",domain=" + domain + ",api=" + gateRequest.getApi());
                }
            }
        }
    }

    private static int getCookieExpireTime(GateInnerRequest innerRequest) {
        if (innerRequest == null || innerRequest.getUserAuthToken() == null) {
            return TokenHandler.EXPIRE_HOUR_SECONDS;
        }

        int expireMs = 0;
        switch (innerRequest.getUserAuthToken().getRole()) {
            case user:
                expireMs = TokenHandler.EXPIRE_HOUR_SECONDS;
                break;
            case admin:
                expireMs = TokenHandler.EXPIRE_HOUR_SECONDS;
                break;
            default:
                expireMs = TokenHandler.EXPIRE_HOUR_SECONDS;
        }
        return expireMs;
    }

    private static boolean isLogoutApi(String api) {
        String logoutApiList = GateConfigSetting.logoutApis;
        if (StringUtils.isNotBlank(api) && logoutApiList.indexOf(api) != -1) {
            return true;
        }
        return false;
    }

}
