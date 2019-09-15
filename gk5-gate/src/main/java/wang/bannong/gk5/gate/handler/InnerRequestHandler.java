package wang.bannong.gk5.gate.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wang.bannong.gk5.gate.config.ApiYmlUtil;
import wang.bannong.gk5.gate.domain.*;

/**
 * Created by wang.bannong on 2018/5/14 下午9:30
 */
@Component
public class InnerRequestHandler {

    private final static Logger LOG = LoggerFactory.getLogger(InnerRequestHandler.class);

    @Autowired
    private AuthTokenHandler authToken;

    /**
     * 封装GateInnerRequest模型
     *
     * @param request
     * @param innerRequest
     * @return
     */
    public GateResult checkAndBuild(GateRequest request, GateInnerRequest innerRequest) {
        final String apiName = request.getApi();
        LOG.info(">>>>> api = {}", apiName);

        if (!ApiYmlUtil.isContain(apiName)) {
            return GateResult.of(ErrorMsgEnum.api_not_exist);
        }
        GateApiInfo gateApiInfo = ApiYmlUtil.getGateApiInfo(apiName);
        innerRequest.setApiInfo(gateApiInfo);

        String mat = request.getMat();
        if(StringUtils.isNotBlank(mat) && GateConfigSetting.mat4Dev.equals(mat)){
            return GateResult.SUCCESS;
        }

        // 获取token模型
        UserAuthToken userAuthToken = getUserAuthToken(mat);

        // 接口权限不做限 但是有的需要根据token获取业务信息
        innerRequest.setUserAuthToken(userAuthToken);

        if (gateApiInfo.isNeedLogin()) {
            if (null == userAuthToken) {
                return GateResult.of(ErrorMsgEnum.user_need_login_token_invalid);
            }

            if (TokenHandler.isExpireToken(userAuthToken)) {
                return GateResult.of(ErrorMsgEnum.user_need_login_token_invalid);
            }
        }

        // 更新token操作
        UserAuthToken userAuthTokenNew = authToken.updateAuthToken(userAuthToken, mat);
        if (userAuthTokenNew != null) {
            request.setMat(userAuthTokenNew.getMat());
        }
        innerRequest.setGateRequest(request);

        innerRequest.setDataStore(request.getBizDataMap());

        return GateResult.SUCCESS;
    }

    private UserAuthToken getUserAuthToken(String mat) {
        if (StringUtils.isBlank(mat)) {
            return null;
        }
        return authToken.getAuthToken(mat);
    }

}
