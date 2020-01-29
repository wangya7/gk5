package wang.bannong.gk5.test.shiro.ctrl;

import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import wang.bannong.gk5.test.shiro.ResultMap;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AccountException.class)
    public ResultMap handleShiroException(Exception ex) {
        return ResultMap.fail().message(ex.getMessage());
    }

}
