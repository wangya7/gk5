package wang.bannong.gk5.test.ntm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = {"wang.bannong"})
public class NtmApplication {
    public static void main(String... args) {
        SpringApplication.run(NtmApplication.class, args);
    }
}