package wang.bannong.gk5.offer.jdk.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    private static ExecutorService executor = initThreadPool();

    public static ExecutorService getExecutor() {
        return executor;
    }

    private static ExecutorService initThreadPool() {
        return new ThreadPoolExecutor(16, 16,
            300, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10240),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    }

}
