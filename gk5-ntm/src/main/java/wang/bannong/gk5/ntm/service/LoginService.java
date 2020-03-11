package wang.bannong.gk5.ntm.service;

import java.util.HashMap;

import wang.bannong.gk5.ntm.biz.handler.AuthTokenHandler;
import wang.bannong.gk5.ntm.biz.rpc.NtmRpcClient;
import wang.bannong.gk5.ntm.common.constant.ApiConfig;
import wang.bannong.gk5.ntm.common.domain.NtmApi;
import wang.bannong.gk5.ntm.common.dto.DynamicDto;
import wang.bannong.gk5.ntm.common.model.AuthToken;
import wang.bannong.gk5.ntm.common.model.NtmInnerRequest;
import wang.bannong.gk5.ntm.common.model.NtmRequest;
import wang.bannong.gk5.ntm.common.model.NtmResult;
import wang.bannong.gk5.util.domain.Subject;

/**
 * Created by bn. on 2019/10/18 4:29 PM
 */
public class LoginService {
    public static NtmResult api(NtmInnerRequest innerRequest) {
        NtmApi ntmApiInfo =innerRequest.getNtmApi();
        NtmRequest ntmRequest = innerRequest.getRequest();
        String apiUnique = ntmApiInfo.getUnique();
        NtmResult result = NtmRpcClient.invoke(ntmApiInfo.getInnerInterface(), ntmApiInfo.getInnerMethod(),
                DynamicDto.of(innerRequest));
        if (!result.isSuccess()) {
            return result;
        }
        HashMap<String, Object> data = result.getData();
        Subject subject = new Subject();
        subject.setId((Long) data.get(ApiConfig.ID));
        subject.setMobile((String) data.get(ApiConfig.MOBILE));
        subject.setName((String) data.get(ApiConfig.NAME));

        AuthTokenHandler.creteAuthToken(subject,
                apiUnique.equals(ApiConfig.LOGIN_API) ? AuthToken.Role.user : AuthToken.Role.admin,
                null, ntmRequest);
        return result;
    }
}
