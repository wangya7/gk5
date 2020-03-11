package wang.bannong.gk5.test.mybatisx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = {"wang.bannong"})
public class MybatisXApplication {
    public static void main(String... args) {
        SpringApplication.run(MybatisXApplication.class, args);
    }
}
