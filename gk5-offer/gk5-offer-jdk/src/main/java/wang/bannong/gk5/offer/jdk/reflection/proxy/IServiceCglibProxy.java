package wang.bannong.gk5.offer.jdk.reflection.proxy;

import net.sf.cglib.proxy.Enhancer;

public class IServiceCglibProxy {

    public static void main(String... args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceImpl.class);
        enhancer.setCallback(new SimpleInterceptor());

        ServiceImpl service = (ServiceImpl) enhancer.create();
        service.sayHi();
        service.sayBye();
    }

}
