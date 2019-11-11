package wang.bannong.gk5.gate.domain;

/**
 * Created by wang.bannong on 2018/6/30 下午1:32
 */
public enum ErrorMsgEnum {
    success(0, "success"),
    stop(1, "停止"),
    api_service_exception(102, "服务异常，请稍后再试"),
    api_param_missing(103, "接口参数[%s]缺失"),
    api_param_illegal(104, "接口参数[%s]不合法"),
    api_timeout(111, "接口请求超时"),
    api_not_exist(118, "接口不存在"),
    sign_error(119, "签名错误"),

    api_config_missing_method(120, "接口配置缺少GET/POST"),
    api_request_method_mismatch(121, "接口请求方法与配置不匹配"),
    api_http_request_methed_illegal(122, "HTTP请求方法有误"),
    api_service_notfound(123, "接口服务未找到"),
    api_request_too_much(125, "请求太频繁了"),
    no_auth_access(127, "无访问权限"),
    user_need_login_token_invalid(129, "登录失效，请重新登录"),
    api_version_invalid(130, "APP版本太低，请先更新"),
    biz_exception(701, "%s"),
    ;

    private int    code;
    private String msg;

    ErrorMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ErrorMsgEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }

    public static ErrorMsgEnum of(int code) {
        for (ErrorMsgEnum errorMsgEnum : values()) {
            if (errorMsgEnum.getCode() == code) {
                return errorMsgEnum;
            }
        }
        return null;
    }

    public static ErrorMsgEnum ofBizErr() {
        return biz_exception;
    }

}
