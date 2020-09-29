package wang.bannong.gk5.offer.jdk.spi;

import org.junit.Test;

import java.util.ServiceLoader;

public class JavaSPITest {
    @Test
    public void hi() {
        ServiceLoader<Robot> loader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        loader.forEach(Robot::sayHi);
    }
}
