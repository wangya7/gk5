package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest {

    @Test
    public void sample() {
        Executor executor = Executors.newFixedThreadPool(20);
    }

}
