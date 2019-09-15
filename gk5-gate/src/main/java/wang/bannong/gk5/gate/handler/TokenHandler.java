package wang.bannong.gk5.gate.handler;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

import wang.bannong.gk5.gate.domain.UserAuthToken;
import wang.bannong.gk5.util.Constant;

/**
 * Created by bn. on 2018/8/26 下午9:33
 */
public class TokenHandler {

    /** APP 一周 */
    public final static int UPDATE_HOUR_SECONDS = 345600; // 四天
    public final static int EXPIRE_HOUR_SECONDS = 604800; // 一周

    /** ADMIN 15分钟 */
    public final static int UPDATE_HOUR_SECONDS_ADMIN = 420;
    public final static int EXPIRE_HOUR_SECONDS_ADMIN = 900;

    public static String generateToken() {
        String param = UUID.randomUUID().toString();
        String tmp = UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString();
        return StringUtils.remove(tmp, Constant.HYPHEN);
    }

    /**
     * 判断token信息是否失效
     *
     * @param userAuthToken
     * @return
     */
    public static boolean isExpireToken(UserAuthToken userAuthToken) {
        long now = System.currentTimeMillis();
        long accessMills = userAuthToken.getAccessTime().getTime();
        long threshold = 0L;
        if (UserAuthToken.Role.user == userAuthToken.getRole()) {
            threshold = EXPIRE_HOUR_SECONDS;
        } else {
            threshold = EXPIRE_HOUR_SECONDS_ADMIN;
        }

        if ((now - accessMills) / 1000 > threshold) return true;
        return false;
    }

    /**
     * 改变token，但不退出登录
     *
     * @param userAuthToken
     * @return
     */
    public static boolean isNeedUpdateToken(UserAuthToken userAuthToken) {
        long now = System.currentTimeMillis();
        long accessMills = userAuthToken.getAccessTime().getTime();
        long threshold = 0L;
        if (UserAuthToken.Role.user == userAuthToken.getRole()) {
            threshold = UPDATE_HOUR_SECONDS;
        } else {
            threshold = UPDATE_HOUR_SECONDS_ADMIN;
        }

        if ((now - accessMills) / 1000 > threshold) return true;

        return false;
    }

}
