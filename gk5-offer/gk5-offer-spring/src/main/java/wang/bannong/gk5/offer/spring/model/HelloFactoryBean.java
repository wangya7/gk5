package wang.bannong.gk5.offer.spring.model;

import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean 是一种工厂 bean，与普通的 bean 不一样，
 * FactoryBean 是一种可以产生 bean 的 bean，
 * FactoryBean 是一个接口，我们可以实现这个接口。
 */
public class HelloFactoryBean implements FactoryBean<Hello> {

    @Override
    public Hello getObject() throws Exception {
        Hello hello = new Hello();
        hello.setContent("Happy New Year!");
        return hello;
    }

    @Override
    public Class<?> getObjectType() {
        return Hello.class;
    }
}
