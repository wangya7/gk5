package wang.bannong.gk5.test.mybatisx.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.test.mybatisx.common.ShiroRole;
import wang.bannong.gk5.test.mybatisx.common.ShiroUser;
import wang.bannong.gk5.test.mybatisx.mapper.ShiroRoleMapper;
import wang.bannong.gk5.test.mybatisx.mapper.ShiroUserMapper;
import wang.bannong.gk5.util.SpringBeanUtils;

@Slf4j
@Component
public class ProfileRealm extends AuthorizingRealm {

    private ShiroUserMapper masterShiroUserMapper;
    private ShiroRoleMapper masterShiroRoleMapper;

    private void setShiroUserDao() {
        if (masterShiroUserMapper == null) {
            masterShiroUserMapper = SpringBeanUtils.getBean("masterShiroUserMapper", ShiroUserMapper.class);
            masterShiroRoleMapper = SpringBeanUtils.getBean("masterShiroRoleMapper", ShiroRoleMapper.class);
        }
    }

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        log.info("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userName = JWTUtil.getUsername(token);
        log.info("用户名【{}】", userName);
        if (userName == null || !JWTUtil.verify(token, userName)) {
            throw new AuthenticationException("token认证失败！");
        }
        setShiroUserDao();

        QueryWrapper<ShiroUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", userName);
        ShiroUser user = masterShiroUserMapper.selectOne(queryWrapper);

        String password = user.getPasswd();
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("————权限认证————");
        String username = JWTUtil.getUsername(principalCollection.toString());
        log.info("用户名【{}】", username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        QueryWrapper<ShiroUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", username);
        ShiroUser user = masterShiroUserMapper.selectOne(queryWrapper);
        String role = user.getRole();
        //每个角色拥有默认的权限

        QueryWrapper<ShiroRole> roleQueryWrapper = new QueryWrapper();
        roleQueryWrapper.eq("role", role);
        ShiroRole shiroRole = masterShiroRoleMapper.selectOne(roleQueryWrapper);

        String rolePermission = shiroRole.getPermission();
        //每个用户可以设置新的权限
        String permission = user.getPermission();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        roleSet.add(role);
        permissionSet.add(rolePermission);
        permissionSet.add(permission);
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
