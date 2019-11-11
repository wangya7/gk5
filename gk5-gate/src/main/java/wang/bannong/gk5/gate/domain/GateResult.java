package wang.bannong.gk5.gate.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by wang.bannong on 2018/5/13 下午8:14
 */
public class GateResult implements Serializable {
    private static final long serialVersionUID = 2478592325643534588L;

    public static final GateResult SUCCESS = GateResult.success();

    private boolean success;
    private String  msg;
    private int     code;
    private Object  data;

    private static GateResult success() {
        GateResult result = new GateResult();
        result.setSuccess(true);
        result.setCode(ErrorMsgEnum.success.getCode());
        return result;
    }

    public static GateResult stop() {
        GateResult result = new GateResult();
        result.setSuccess(true);
        result.setCode(ErrorMsgEnum.stop.getCode());
        return result;
    }

    public static GateResult succ(String msg) {
        GateResult result = success();
        result.setData(null);
        result.setMsg(msg);
        return result;
    }

    public static GateResult of(Object model) {
        GateResult result = success();
        result.setData(model);
        return result;
    }

    public static GateResult of(String msg) {
        ErrorMsgEnum errorMsgEnum = ErrorMsgEnum.ofBizErr();
        GateResult result = new GateResult();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setCode(errorMsgEnum.getCode());
        return result;
    }

    public static GateResult of(String msg, int code) {
        GateResult result = new GateResult();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    @JSONField(serialize = false)
    public boolean isStop() {
        return isSuccess() && "stop".equals(this.getCode());
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @SuppressWarnings("unchecked")
    public <T> T getData() {
        return (T) data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static GateResult of(ErrorMsgEnum errorMsgEnum) {
        return of(errorMsgEnum.getMsg(), errorMsgEnum.getCode());
    }

    public static GateResult of(ErrorMsgEnum errorMsgEnum, String... args) {
        return of(String.format(errorMsgEnum.getMsg(), args), errorMsgEnum.getCode());
    }

    @Override
    public String toString() {
        return "GateResult{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
