package wang.bannong.gk5.sparrow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by bn. on 2019/9/15 11:59 AM
 */
@SpringBootApplication
@ComponentScan("wang.bannong.gk5.sparrow.*")
public class SparrowDemoApplication {
    public static void main(String... args) {
        SpringApplication.run(SparrowDemoApplication.class, args);
    }
}
