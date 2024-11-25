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
        int processorSize = Runtime.getRuntime().availableProcessors();

        return new ThreadPoolExecutor(processorSize * 2,
                processorSize * 25,
            10, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    }

}
