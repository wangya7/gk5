package wang.bannong.gk5.offer.spring.model;

public class StaticHelloFactory {
    public static Hello getHello() {
        Hello hello = new Hello();
        hello.setContent("created by StaticHelloFactory");
        return hello;
    }
}
