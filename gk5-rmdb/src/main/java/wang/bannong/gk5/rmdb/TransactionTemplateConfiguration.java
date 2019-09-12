package wang.bannong.gk5.rmdb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wang.bannong(inc11003307@gmail.com)
 * @create 2017-06-22 15:23
 */
@Configuration
public class TransactionTemplateConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager txManager) {
        return new TransactionTemplate(txManager);
    }

}
