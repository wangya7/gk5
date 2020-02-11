package wang.bannong.gk5.test.ctrl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class UserBean1FactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        //注册bean实例
        log.info("UserBean1FactoryPostProcessor register personManager >>>>>>>>>>>>>>>>");
        WillRegisterBeanBO personManager1 = new WillRegisterBeanBO();
        beanFactory.registerSingleton("personManager1", personManager1);
        log.info("register personManager 1 finished");
    }
}
