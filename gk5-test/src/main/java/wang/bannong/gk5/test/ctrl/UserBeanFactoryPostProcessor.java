package wang.bannong.gk5.test.ctrl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        //注册bean实例
        log.info("register personManager >>>>>>>>>>>>>>>>");
        WillRegisterBeanBO personManager1 = new WillRegisterBeanBO();
        beanFactory.registerSingleton("personManager1", personManager1);
        log.info("register personManager 1 finished");

        WillRegisterBeanBO personManager2 = new WillRegisterBeanBO();
        beanFactory.registerSingleton("personManager2", personManager2);
        log.info("register personManager 2 finished");

        WillRegisterBeanBO personManager3 = new WillRegisterBeanBO();
        beanFactory.registerSingleton("personManager3", personManager3);
        log.info("register personManager 3 finished");

    }
}
