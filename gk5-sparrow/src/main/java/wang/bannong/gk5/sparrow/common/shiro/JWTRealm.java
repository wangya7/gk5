package wang.bannong.gk5.sparrow.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import wang.bannong.gk5.sparrow.common.utils.JWTUtils;
import wang.bannong.gk5.sparrow.common.utils.TypeUtils;
import wang.bannong.gk5.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.sparrow.framework.utils.ApiAssert;
import wang.bannong.gk5.sparrow.service.IResourceService;
import wang.bannong.gk5.sparrow.service.IAdminService;

/**
 * JWT Realm 适用于shiro
 */
@Service
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private IAdminService userService;

    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer uid = JWTUtils.getUid(principals.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleIds = userService.getRoleIds(uid).stream().map(TypeUtils::castToString).collect(Collectors.toSet());
        simpleAuthorizationInfo.addRoles(roleIds);
        simpleAuthorizationInfo.addStringPermissions(resourceService.getUserPerms(uid));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getPrincipal();
        // 判断Token是否过期
        ApiAssert.isFalse(ErrorCodeEnum.UNAUTHORIZED, JWTUtils.isExpired(token));
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
