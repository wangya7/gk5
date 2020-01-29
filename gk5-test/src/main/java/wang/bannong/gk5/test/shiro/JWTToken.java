package wang.bannong.gk5.test.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import lombok.Data;

@Data
public class JWTToken implements AuthenticationToken {
    private static final long serialVersionUID = 220444560405438L;

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return getToken();
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }
}
