package wang.bannong.gk5.offer.spring.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class NewsProvider2 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private News news;

    /** 每次都从 applicationContext 中获取一个新的 bean */
    public News getNews() {
        return applicationContext.getBean("news", News.class);
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
