package wang.bannong.gk5.gate.handler;

import wang.bannong.gk5.gate.domain.UserAuthToken;

/**
 * Created by bn. on 2018/9/6 下午5:24
 */
public interface AuthTokenHandler {
    UserAuthToken updateAuthToken(UserAuthToken userAuthToken, String mat);
    UserAuthToken getAuthToken(String mat);
}