package wang.bannong.gk5.offer.jdk.reflection.aop;

public class ServiceA {
    @SimpleInject
    ServiceB b;

    public void callB() {
        b.action();
    }

    public ServiceB getB() {
        return b;
    }
}
