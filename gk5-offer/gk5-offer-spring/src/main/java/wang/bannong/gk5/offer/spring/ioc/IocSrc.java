package wang.bannong.gk5.offer.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wang.bannong.gk5.offer.spring.model.Dog;

public class IocSrc {

    /**
     * getBean源码分析
     */
    @Test
    public void getBeanTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-demo1.xml");

        /**
         *
         * org.springframework.beans.factory.BeanFactory#getBean(java.lang.String)
         *     org.springframework.beans.factory.support.AbstractBeanFactory#getBean(java.lang.String)
         *         org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean(java.lang.String, java.lang.Class, java.lang.Object[], boolean)
         *             org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String) 获取单例Bean
         *                 org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String, boolean) 获取单例Bean，涉及到处理循环依赖的逻辑
         *
         * 1. 转换 beanName
         * 2. 从缓存中获取实例 getSingleton(beanName)，其中涉及到循环依赖的处理
         * 3. 如果实例不为空，且 args = null。调用 getObjectForBeanInstance 方法，并按 name 规则返回相应的 bean 实例
         * 4. 若上面的条件不成立，则到父容器中查找 beanName 对有的 bean 实例，存在则直接返回
         * 5. 若父容器中不存在，则进行下一步操作 – 合并 BeanDefinition
         * 6. 处理 depends-on 依赖
         * 7. 根据不同的bean类型加载【重要】
         *    7.1 singleton getSingleton  org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String, org.springframework.beans.factory.ObjectFactory)
         *        7.1.1 先从 singletonObjects 集合获取 bean 实例，若不为空，则直接返回
         *        7.1.2 若为空，进入创建 bean 实例阶段。先将 beanName 添加到 singletonsCurrentlyInCreation 表示正在创建
         *        7.1.3 通过 getObject 方法调用 createBean 方法创建 bean 实例
         *        7.1.4 将 beanName 从 singletonsCurrentlyInCreation 集合中移除
         *        7.1.5 将 <beanName, singletonObject> 映射缓存到 singletonObjects 集合中并
         *              从其他集合（比如 earlySingletonObjects）中移除 singletonObject 记录
         *    7.2 createBean创建bean  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
         *        7.2.1 解析 bean 类型
         *        7.2.2 处理 lookup-method 和 replace-method 配置
         *        7.2.3 在 bean 初始化前应用后置处理，若后置处理返回的 bean 不为空，则直接返回
         *        7.2.4 若上一步后置处理返回的 bean 为空，则调用 doCreateBean 创建 bean 实例  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
         *            1. 从缓存中获取 BeanWrapper 实现类对象，并清理相关记录
         *            2. 若未命中缓存，则通过 createBeanInstance 创建 bean 实例，并将实例包裹在 BeanWrapper 实现类对象中返回  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
         *               1. 检测类的访问权限，若禁止访问，则抛出异常
         *               2. 若工厂方法不为空，则通过工厂方法构建 bean 对象，并返回结果
         *               3. 若构造方式已解析过，则走快捷路径构建 bean 对象，并返回结果
         *               4. 如第三步不满足，则通过组合条件决定使用哪种方式构建 bean 对象
         *                  这里有三种构造 bean 对象的方式，如下：
         *                      通过“工厂方法”的方式构造 bean 对象
         *                      通过“构造方法自动注入”的方式构造 bean 对象
         *                      通过“默认构造方法”的方式构造 bean 对象
         *            3. 应用 MergedBeanDefinitionPostProcessor 后置处理器相关逻辑
         *            4. 根据条件决定是否提前暴露 bean 的早期引用（early reference），用于处理循环依赖问题
         *            5. 调用 populateBean 方法向 bean 实例中填充属性
         *            6. 调用 initializeBean 方法完成余下的初始化工作
         *            7. 注册销毁逻辑
         *    7.3 调用 getObjectForBeanInstance 方法，并按 name 规则返回相应的 bean 实例，主要是处理FactoryBean创建Bean的方式。
         * 8. 按需转换 bean 类型，并返回转换后的 bean 实例。
         *
         *
         * 说明一下第7步其实细节众多，这里面只是大概的介绍一下，是大概哈！
         *
         */
        Dog dog = (Dog) applicationContext.getBean("dog");
        dog.run();
    }

    /**
     * 链接: http://www.tianxiaobo.com/2018/06/08/Spring-IOC-%E5%AE%B9%E5%99%A8%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90-%E5%BE%AA%E7%8E%AF%E4%BE%9D%E8%B5%96%E7%9A%84%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95/
     *
     * 循环依赖代码分析
     * 重点分析这个类：
     * org.springframework.beans.factory.support.DefaultSingletonBeanRegistry
     *
     * Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);  用于存放完全初始化好的 bean，从该缓存中取出的 bean 可以直接使用
     * Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16); 存放 bean 工厂对象，用于解决循环依赖
     * Map<String, Object> earlySingletonObjects = new HashMap<>(16);        存放原始的 bean 对象（尚未填充属性），用于解决循环依赖
     *                                                                       所谓的”早期引用“是指向原始对象的引用。所谓的原始对象是指刚创建好的对象，但还未填充属性
     * Set<String> singletonsCurrentlyInCreation =
     * 			Collections.newSetFromMap(new ConcurrentHashMap<>(16));      正在创建的 bean
     */
    @Test
    public void circularDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-demo1.xml");
        System.out.println("model1==> " + context.getBean("model1"));
        System.out.println("model2==> " + context.getBean("model2"));
    }
}
