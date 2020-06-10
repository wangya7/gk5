package wang.bannong.gk5.offer.jdk.reflection.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SimpleInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("proxy....bb");
        Object res = methodProxy.invokeSuper(o, objects);
        System.out.println("proxy....dd");
        return res;
    }
}
