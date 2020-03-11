package wang.bannong.gk5.test.mybatisx.ctrl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(2)
public class UserBean2FactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;

        //注册bean实例
        log.info("UserBean2FactoryPostProcessor register personManager >>>>>>>>>>>>>>>>");

        WillRegisterBeanBO personManager = new WillRegisterBeanBO();
        beanFactory.registerSingleton("personManager2", personManager);
        log.info("register personManager 2 finished");
    }
}

