package wang.bannong.gk5.ntm.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wang.bannong on 2018/7/10 下午9:26
 */
public final class ParamsCheckUtils {

    /**
     * 正则表达式：验证手机号
     */
    // public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String REGEX_MOBILE = "^(1)\\d{10}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[_\\-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "^[1-8]\\d{16}([0-9Xx])$";

    /**
     * 正则表达式：验证密码设置
     */
    public static final String REGEX_PASSWORD = "\\w{6,15}";

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验密码
     *
     * @param passwd
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String passwd) {
        return Pattern.matches(REGEX_PASSWORD, passwd);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    /**
     * 允许中英文数字
     *
     * @return
     */
    public static boolean checkSign(String sign) {
        String str = "[\\w\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(sign);
        return m.matches();
    }

    public static void main(String... args) {
        if (isEmail("inc-bn@163.com")) {
            System.out.println("T");
        } else {
            System.out.println("F");
        }
    }

}
