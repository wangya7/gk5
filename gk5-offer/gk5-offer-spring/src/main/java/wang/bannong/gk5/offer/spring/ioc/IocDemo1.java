package wang.bannong.gk5.offer.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wang.bannong.gk5.offer.spring.model.AnimalsAction;
import wang.bannong.gk5.offer.spring.model.NewsProvider;
import wang.bannong.gk5.offer.spring.model.NewsProvider2;

public class IocDemo1 {

    /**
     * autowire
     * <p>
     * byName、byType 和 constructor
     */
    @Test
    public void s1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-demo1.xml");

        AnimalsAction animalsAction1 = (AnimalsAction) applicationContext.getBean("animalsAction-without-autowire");
        animalsAction1.run();

        AnimalsAction animalsAction2 = (AnimalsAction) applicationContext.getBean("animalsAction-with-autowire");
        animalsAction2.run();
    }

    /**
     * FactoryBean
     * <p>
     * FactoryBean 是一种工厂 bean，与普通的 bean 不一样，FactoryBean 是一种可以产生 bean 的 bean。
     * FactoryBean 是一个接口，我们可以实现这个接口。
     */
    @Test
    public void s2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-demo1.xml");

        /**
         * context.getBean("helloFactoryBean") 获取的是 HelloFactoryBean 创建的 Hello 对象，
         * 该对象是 HelloFactoryBean 的 getObject 方法所创建的。
         *
         * 如果我们想获取 HelloFactoryBean 本身，则可以在 helloFactory 前加上一个前缀&，即&helloFactory。
         */
        System.out.println("helloFactoryBean -> " + context.getBean("helloFactoryBean"));
        System.out.println("&helloFactoryBean -> " + context.getBean("&helloFactoryBean"));
    }

    /**
     * factory-method
     * <p>
     * factory-method 可用于标识静态工厂的工厂方法（工厂方法是静态的）
     */
    @Test
    public void s3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-demo1.xml");

        /**
         * 对于非静态工厂，需要使用 factory-bean 和 factory-method 两个属性配合。
         * 关于 factory-bean 这里就不继续说了，留给大家自己去探索吧。
         */
        System.out.println("staticHelloFactory -> " + context.getBean("staticHelloFactory"));
    }

    /**
     * lookup-method
     * <p>
     * 这个重要一点 但是不是经常使用到！
     * 先介绍一下背景。
     * 我们通过 BeanFactory getBean 方法获取 bean 实例时，对于 singleton 类型的 bean，BeanFactory
     * 每次返回的都是同一个 bean。对于 prototype 类型的 bean，BeanFactory 则会返回一个新的 bean。现在
     * 考虑这样一种情况，一个 singleton 类型的 bean 中有一个 prototype 类型的成员变量。BeanFactory 在
     * 实例化 singleton 类型的 bean 时，会向其注入一个 prototype 类型的实例。但是 singleton 类型的
     * bean 只会实例化一次，那么它内部的 prototype 类型的成员变量也就不会再被改变。但如果我们每次从
     * singleton bean 中获取这个 prototype 成员变量时，都想获取一个新的对象。这个时候怎么办？举个例子
     * （该例子源于《Spring 揭秘》一书），我们有一个新闻提供类（NewsProvider），这个类中有一个新闻类（News）
     * 成员变量。我们每次调用 getNews 方法都想获取一条新的新闻。这里我们有两种方式实现这个需求，一种方式是让
     * NewsProvider 类实现 ApplicationContextAware 接口（实现 BeanFactoryAware 接口也是可以的），
     * 每次调用 NewsProvider 的 getNews 方法时，都从 ApplicationContext 中获取一个新的 News 实例，
     * 返回给调用者。第二种方式就是这里的 lookup-method 了，Spring 会在运行时对 NewsProvider 进行增强，
     * 使其 getNews 可以每次都返回一个新的实例。
     * <p>
     * 1. 实现 ApplicationContextAware 接口
     * 2. 使用 lookup-method 特性
     */
    @Test
    public void s4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-demo1.xml");
        /**
         * newsProvider.getNews() 方法两次返回的结果都是一样的，这个是不满足要求的。
         */
        NewsProvider newsProvider = (NewsProvider) context.getBean("newsProvider");
        System.out.println(newsProvider.getNews());
        System.out.println(newsProvider.getNews());

        /**
         * 实现 ApplicationContextAware 接口 返回结果不一致啦
         */
        NewsProvider2 newsProvider2 = (NewsProvider2) context.getBean("newsProvider2");
        System.out.println(newsProvider2.getNews());
        System.out.println(newsProvider2.getNews());

        /**
         * 配置 xml 中使用 lookup-method 特性 返回结果不一致啦
         */
        NewsProvider newsProvider3 = (NewsProvider) context.getBean("newsProvider3");
        System.out.println(newsProvider3.getNews());
        System.out.println(newsProvider3.getNews());
    }

    /**
     * depends-on
     *
     * 当一个 bean 直接依赖另一个 bean，可以使用 <ref/> 标签进行配置。
     * 不过如某个 bean 并不直接依赖于其他 bean，但又需要其他 bean 先实例化好，
     * 这个时候就需要使用 depends-on 特性了。
     *
     * 这里有两个简单的类，其中 Hello 需要 World 在其之前完成实例化。相关配置如下：
     * <bean id="hello" class="xyz.coolblog.depnedson.Hello" depends-on="world"/>
     * <bean id="world" class="xyz.coolblog.depnedson.World" />
     */
    @Test
    public void s5() {
    }

    /**
     * BeanPostProcessor
     * BeanPostProcessor 是 bean 实例化时的后置处理器
     * BeanPostProcessor 是 Spring 框架的一个扩展点，通过实现 BeanPostProcessor 接口，
     * 我们就可插手 bean 实例化的过程。比如大家熟悉的 AOP 就是在 bean 实例后期间将切面逻辑
     * 织入 bean 实例中的，AOP 也正是通过 BeanPostProcessor 和 IOC 容器建立起了联系。
     */
    @Test
    public void s6() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-bean-post-processor.xml");
    }
}
