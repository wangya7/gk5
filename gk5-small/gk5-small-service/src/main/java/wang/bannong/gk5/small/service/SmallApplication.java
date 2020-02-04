package wang.bannong.gk5.small.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"wang.bannong.gk5"})
@ImportResource("classpath:applicationContext.xml")
public class SmallApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmallApplication.class, args);
    }
}
