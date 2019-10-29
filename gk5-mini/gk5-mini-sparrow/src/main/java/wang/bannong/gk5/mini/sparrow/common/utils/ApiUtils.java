package wang.bannong.gk5.mini.sparrow.common.utils;

import java.util.Objects;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.mini.sparrow.common.spring.ApplicationUtils;
import wang.bannong.gk5.mini.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.mini.sparrow.framework.utils.ApiAssert;
import wang.bannong.gk5.mini.sparrow.cons.APICons;

/**
 * API工具类
 *
 * @author Caratacus
 */
@SuppressWarnings("ALL")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public abstract class ApiUtils {

    /**
     * 获取当前用户id
     */
    public static Integer currentUid() {
        Integer uid = (Integer) ApplicationUtils.getRequest().getAttribute(APICons.API_UID);
        if (Objects.isNull(uid)) {
            String token = ApplicationUtils.getRequest().getHeader("Authorization");
            ApiAssert.notNull(ErrorCodeEnum.UNAUTHORIZED, token);
            token = token.replaceFirst("Bearer ", "");
            Claims claims = JWTUtils.getClaim(token);
            ApiAssert.notNull(ErrorCodeEnum.UNAUTHORIZED, claims);
            return claims.get(JWTUtils.UID, Integer.class);
        }
        return uid;
    }

}
