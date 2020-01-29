package wang.bannong.gk5.test.shiro;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultMap implements Serializable {
    private static final long serialVersionUID = 4814533862668767514L;

    private int    code;
    private String message;

    public ResultMap() {
    }

    public ResultMap(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultMap success() {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(0);
        return resultMap;
    }

    public static ResultMap fail() {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(1);
        return resultMap;
    }

    public ResultMap message(String message) {
        this.setMessage(message);
        return this;
    }
}
