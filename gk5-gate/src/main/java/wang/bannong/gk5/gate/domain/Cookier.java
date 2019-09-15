package wang.bannong.gk5.gate.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.bannong.gk5.util.Constant;

/**
 * Created by bn. on 2018/9/5 下午7:56
 */
public class Cookier implements Serializable {

    private static final long serialVersionUID = 7672546593395175015L;

    private static final Logger LOG = LoggerFactory.getLogger(Cookier.class);

    private Map<String, Cookie> cookieMap;

    public static Cookier of(HttpServletRequest request) {
        Cookier helper = new Cookier();
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        helper.cookieMap = cookieMap;
        return helper;
    }

    public Cookie getCookieByName(String name) {
        if (cookieMap == null) return null;
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    public String getCookieValueByName(String name) {
        if (cookieMap == null) return null;
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            if (cookie != null) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public void delCookie(HttpServletResponse response, String name) {
        if (cookieMap == null || cookieMap.isEmpty()) return;
        if (cookieMap.containsKey(name)) {
            Cookie cookie = getCookieByName(name);
            cookie.setValue(null);
            cookie.setMaxAge(0);// 立即销毁cookie
            cookie.setPath(Constant.SEPARATOR);
            response.addCookie(cookie);
        }
    }

    public void setCookie(HttpServletResponse response, String name, String value, int time, String domain) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        try {
            // tomcat下多应用共享
            cookie.setPath(Constant.SEPARATOR);
            // 多域名共享
            cookie.setDomain(domain);
            // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
            URLEncoder.encode(value, Constant.UTF8);
        } catch (Exception e) {
            LOG.error(">>>encode cookie exception", e);
        }
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
    }

    @Override
    public String toString() {
        return "Cookier{" +
                "cookieMap=" + cookieMap +
                '}';
    }
}
