package wang.bannong.gk5.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"hz.qxbn"})
public class TestApplication {
    public static void main(String...args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
