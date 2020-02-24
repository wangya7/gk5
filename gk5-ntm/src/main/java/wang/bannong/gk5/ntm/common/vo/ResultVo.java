package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;

import lombok.Data;
import wang.bannong.gk5.ntm.common.constant.CommonConstant;

@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -491212860056229342L;
    // 响应编码
    private Integer code;
    // 响应消息
    private String msg;
    // 返回的vo
    private T data;

    public ResultVo(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public ResultVo(){
    	this.code = CommonConstant.SUCCESS_CODE;
    }
}
