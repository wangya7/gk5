package wang.bannong.gk5.ntm.common.constant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * " 1-GET 2-POST 3-PUT 4-PATCH 5-DELETE
 * 
 * @author tuanzuo
 * @date 2019年7月10日
 */
public enum MethodEnum {

    GET((byte) 1, "GET"), 
    POST((byte) 2, "POST"), 
    PUT((byte) 3, "PUT"), 
    PATCH((byte) 4,"PATCH"), 
    DELETE((byte) 5, "DELETE");

    private Byte code;
    private String msg;

    MethodEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static final Map<Byte, MethodEnum> methodEnumMap = new HashMap<>();

    static {
        for (MethodEnum gen : EnumSet.allOf(MethodEnum.class)) {
            methodEnumMap.put(gen.getCode(), gen);
        }
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
