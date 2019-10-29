package wang.bannong.gk5.mini.sparrow.framework.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import wang.bannong.gk5.mini.sparrow.framework.utils.LogUtils;

/**
 * Controller统一切点日志处理
 */
@Aspect
public class LogRecordAspect {

    @Pointcut("execution(public * org.crown.controller.*RestController.*(..))")
    @SuppressWarnings("EmptyMethod")
    public void pointCut() {
    }

    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(Object ret) {
        LogUtils.doAfterReturning(ret);
    }

}
