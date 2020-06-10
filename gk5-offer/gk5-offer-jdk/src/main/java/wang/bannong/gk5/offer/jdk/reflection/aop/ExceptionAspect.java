package wang.bannong.gk5.offer.jdk.reflection.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

// ExceptionAspect也是一个切面，它负责类ServiceB的异常切面。
@Aspect({ServiceB.class})
public class ExceptionAspect {
    public static void exception(Object object,
                                 Method method, Object[] args, Throwable e) {
        System.err.println("exception when calling: " + method.getName()
                + "," + Arrays.toString(args));
    }
}
