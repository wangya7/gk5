package wang.bannong.gk5.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"wang.bannong"})
@MapperScan("wang.bannong.gk5.test.mapper")
public class TestApplication {
    public static void main(String...args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
