package wang.bannong.gk5.sparrow.framework.exception;

import wang.bannong.gk5.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.sparrow.framework.model.ErrorCode;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final ErrorCode errorCode;

    public ApiException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.msg());
        this.errorCode = errorCodeEnum.convert();
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getError());
        this.errorCode = errorCode;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
