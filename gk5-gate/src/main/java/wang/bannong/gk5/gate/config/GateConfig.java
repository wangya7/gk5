package wang.bannong.gk5.gate.config;

import wang.bannong.gk5.util.Constant;

/**
 * Created by wang.bannong on 2018/7/1 上午12:27
 */
public abstract class GateConfig {

    public final static String api     = "api";
    public final static String v       = "v";
    public final static String ttid    = "ttid";
    public final static String did     = "did";
    public final static String ts      = "ts";
    public final static String sign    = "sign";
    public final static String data    = "data";
    public final static String mat     = "mat";

    public volatile static String domains    = Constant.BLANK;
    public volatile static String mat4Dev    = "inm";
    public volatile static String sign4Dev   = "inm";
    public volatile static String logoutApis = Constant.BLANK;

    /** CORS */
    public volatile static boolean corsOriginDefaultSetting = false;
    public volatile static String  corsOriginHosts          = Constant.BLANK;
    public volatile static String  corsOriginHostsDef       = Constant.BLANK;

    public volatile static boolean isWriteCookieAlways = true;
    public volatile static boolean logResponse         = false;

    /** APP 一周 */
    public static int UPDATE_HOUR_SECONDS = 345600; // 四天
    public static int EXPIRE_HOUR_SECONDS = 604800; // 一周

    /** ADMIN 15分钟 */
    public static int UPDATE_HOUR_SECONDS_ADMIN = 420;
    public static int EXPIRE_HOUR_SECONDS_ADMIN = 900;

    /** 网关加密项 和 TS的周期 */
    public static String API_PARAMS_CANNOT_NULL = "api,v,ttid,did,ts,sign,method";
    public static long   DIS_TS_THRESHOLD       = 300;
    public static String SIGN_FORMAT            = "api=%s&data=%s&did=%s&mat=%s&ts=%s&ttid=%s&v=%s";


    /**
     * RSA加密
     */
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsLp2NLUOqRdWItvH3zWJsdWvveUvWj/B\n" +
            "+XRzRuzDTvsaMgHAfE7zpjEqFj4/BVw4LThOseLXwsxt4RHxEpykL/OMpMUz8MJmN3gKVID43tN7\n" +
            "5H0b6IL46ymMNwOcPt9EfEGdnUaTJEwxG4VpKIGWxDOUnEybS7QuUztJlNMByxEYR69dLUSSOCM+\n" +
            "TbQNIVeVVXixPhI43pb5umR/3mQ+XHTuAZ2zoMA4OsrEO/052Wdv66dmNtaK8eTwwrGcmHOCi5fQ\n" +
            "SWT2gi3Vq592461w3H8cbdfHU+3cgS0HkG4u3D/cU8eK8lUSUKxIY9wcv2nDdNWpYOwi5oY06sNF\n" +
            "9UpSwQIDAQAB";
    public static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwunY0tQ6pF1Yi28ffNYmx1a+9\n" +
            "5S9aP8H5dHNG7MNO+xoyAcB8TvOmMSoWPj8FXDgtOE6x4tfCzG3hEfESnKQv84ykxTPwwmY3eApU\n" +
            "gPje03vkfRvogvjrKYw3A5w+30R8QZ2dRpMkTDEbhWkogZbEM5ScTJtLtC5TO0mU0wHLERhHr10t\n" +
            "RJI4Iz5NtA0hV5VVeLE+Ejjelvm6ZH/eZD5cdO4BnbOgwDg6ysQ7/TnZZ2/rp2Y21orx5PDCsZyY\n" +
            "c4KLl9BJZPaCLdWrn3bjrXDcfxxt18dT7dyBLQeQbi7cP9xTx4ryVRJQrEhj3By/acN01alg7CLm\n" +
            "hjTqw0X1SlLBAgMBAAECggEBAKXDj6IqfYDO2cVCfrv3fJroQroMIRk2mlvABWxuIVeQoWZsTD6M\n" +
            "0257yO0qH3SIpkyL9wxVKGmX1Cx65rK9aCdt+uqF6g0WWkgG+TF/828V0KxlSOsTBiWVR+ZGJaS3\n" +
            "SIaACTV+//7Y1Grq/NK4klCfzPG5zSP58kWxY3gLXzA23PIDw9phTmDXJj0n7Xg73Ydf9wPbjpih\n" +
            "etDVazvbJckZoyFhtmSBhsN6vEEwGo8MvuiCLa8zBD4iJ49DZ9mHzsZ5WPmVpP32uhjsEkpZUUcI\n" +
            "U+TKY5fyOfZgYhqyVFPsYRblZNiMHDqHKJNtfNSHHx1PMB2PqnlAGGWoLGvP9kECgYEA6Pi2d2bw\n" +
            "cJefQJsSr2yj4ongiCCvR4iHH/QR2nQGsqFJpXalUjG4MIpK/C/992oGkYwW9o6++djv0lh2BHb5\n" +
            "sYBcH6SOM/OVjNomEOVHq5UV1eUE03Qoxnq7OVzBBY+2rS+1zRUaGvY2wKZrEBK8hPuITPUpxDYL\n" +
            "UBWk8+HJHikCgYEAwjKHjAYIiHMT9sAAhyWTsdlTuhJRoPSCxR8OZrEbd9k00btc9tDWJmDLrXXp\n" +
            "utHMXVVLRS9O9CN4q53y0WkUIh5TwmOvlN6DmDm5nonYcYhBUiyHGyC989gVSrSThA1YDxKVniwN\n" +
            "obb5KuSK3VToodnff33ll/gaorVj8YiF8tkCgYEAuFzyUOVWi1OBxGezzr1jqAfCdxeefjZt8Hx+\n" +
            "kGjXUA9RL68Hv1b3Uvptwsrb6SqvjgjVt0JZoXDqwIQeyQO6L5zLyoK3wicWh0OvchlvsTom8IDv\n" +
            "Y8L97av8c7WSTqtfPgiOj4AOi52i0Kwh97NMGtTPFU/CQLMgGr02L4XTWZkCgYBPs09AH4XHAnvS\n" +
            "DFDrJnfsSQZ82mHYDiHRZwKREOT2FhYAccYgoesSCy3sffU0MECQmZ3vwg/EUP6IaKWsXlTd/9Ze\n" +
            "dG0uSvh1NFPDBk3nooU0hYPZlWGAP1HVvDDCxHs8jGMNbIUXtrGicfOwDNYA/xskBvaAnVnJJ2ri\n" +
            "SSa4WQKBgBrL1+GRnY3N6Oxcbr0qikXedox2vVDhPg+9XVWX/L2/Wu8LoJQHJrn3OZXeCSJplxhf\n" +
            "1V+H4Tu5E7BRhOb+96gb+hMsk/xh57ou3sIr4tU2izjXnEwyLv46ZRIIo/iP+5Buq0Pdf+6PQk+V\n" +
            "HVZcyGWLbfjvf+1oIjpBP2qlg3Eo";

}
