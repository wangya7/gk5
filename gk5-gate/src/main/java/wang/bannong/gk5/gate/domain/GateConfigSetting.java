package wang.bannong.gk5.gate.domain;

import wang.bannong.gk5.util.Constant;

/**
 * Created by wang.bannong on 2018/7/1 上午12:27
 */
public abstract class GateConfigSetting {

    public volatile static String mat = "mat";
    public volatile static String domains = Constant.BLANK;
    public volatile static String mat4Dev = "inm";
    public volatile static String sign4Dev = "inm";
    public volatile static String logoutApis = Constant.BLANK;

    public volatile static boolean corsOriginDefaultSetting = false;
    public volatile static String  corsOriginHosts = Constant.BLANK;
    public volatile static String  corsOriginHostsDef = Constant.BLANK;

    public volatile static boolean isWriteCookieAlways = true;
    public volatile static boolean logResponse = false;
}
