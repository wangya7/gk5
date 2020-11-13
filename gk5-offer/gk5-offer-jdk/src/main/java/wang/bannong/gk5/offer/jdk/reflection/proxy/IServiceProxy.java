package wang.bannong.gk5.offer.jdk.reflection.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IServiceProxy implements InvocationHandler {

    private final Object realObject;

    public IServiceProxy(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy....bb");
        Object res = method.invoke(realObject, args);
        System.out.println("proxy....dd");
        return res;
    }

    public static void main(String... args) {
        IService service = new ServiceImpl();
        // IService serviceProxy = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
        //         new Class<?>[]{IService.class},
        //         new IServiceProxy(service));


        /**
         * 分析下面的过程
         * 1. 通过Proxy.getProxyClass创建代理类定义，类定义会被缓存
         * 2. 获取代理类的构造方法，构造方法有一个InvocationHandler类型的参数
         * 3. 创建InvocationHandler对象，创建代理类对象
         *
         *
         *
         * Java SDK代理面向的是一组接口，它为这些接口动态创建了一个实现类，接口的具体实现逻辑是通过自定义的
         * InvocationHandler实现的，这个实现是自定义的，也就是说，其背后都不一定有真正被代理的对象，
         * 也可能多个实际对象，根据情况动态选择。cglib代理面向的是一个具体的类，它动态创建了一个新类，继承了该类，
         * 重写了其方法。
         *
         * 从代理的角度看，Java SDK代理的是对象，需要先有一个实际对象，自定义的InvocationHandler引用该对象，
         * 然后创建一个代理类和代理对象，客户端访问的是代理对象，代理对象最后再调用实际对象的方法，cglib代理
         * 的是类，创建的对象只有一个。
         *
         * 如果目的都是为一个类的方法增强功能，Java SDK要求该类必须有接口，且只能处理接口中的方法，cglib没有这个限制。
         *
         */
        IService serviceProxy = null;
        try {
            Class<?> cls = Proxy.getProxyClass(IService.class.getClassLoader(),
                    new Class[]{IService.class});
            Constructor<?> ctor = cls.getConstructor(new Class[]{InvocationHandler.class});
            InvocationHandler invocationHandler = new IServiceProxy(service);
            serviceProxy = (IService) ctor.newInstance(invocationHandler);

            // serviceProxy = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
            //         new Class<?>[]{IService.class},
            //         new IServiceProxy(service));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (serviceProxy != null) {
            serviceProxy.sayHi();
            serviceProxy.sayBye();
        }
    }
}
