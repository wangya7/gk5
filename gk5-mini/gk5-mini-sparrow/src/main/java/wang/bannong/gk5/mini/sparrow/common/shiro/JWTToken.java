package wang.bannong.gk5.mini.sparrow.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWTToken
 */
public class JWTToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;

    private final String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
