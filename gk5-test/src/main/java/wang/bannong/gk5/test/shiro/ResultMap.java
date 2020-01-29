package wang.bannong.gk5.test.shiro;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultMap implements Serializable {
    private static final long serialVersionUID = 4814533862668767514L;

    private int    code;
    private Object data;
    private String message;

    public ResultMap() {
    }

    public ResultMap(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultMap success(Object data) {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(0);
        resultMap.setData(data);
        return resultMap;
    }

    public static ResultMap success(String message) {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(0);
        resultMap.setMessage(message);
        return resultMap;
    }

    public static ResultMap fail(String message) {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(1);
        resultMap.setMessage(message);
        return resultMap;
    }

}