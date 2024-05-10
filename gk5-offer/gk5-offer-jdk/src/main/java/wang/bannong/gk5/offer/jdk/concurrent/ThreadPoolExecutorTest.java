package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    @Test
    public void sample() {
        int COUNT_BITS = Integer.SIZE - 3;
        int RUNNING = -1 << COUNT_BITS; // -1 * 2^9
        int SHUTDOWN = 0 << COUNT_BITS; // 0
        int STOP = 1 << COUNT_BITS;
        int TIDYING = 2 << COUNT_BITS;
        int TERMINATED = 3 << COUNT_BITS;

        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
    }


    @Test
    public void arrayBlockingQueue() throws InterruptedException {
        // ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Thread provider = new Thread(() -> {
            while (true) {
                Integer value = new Random().nextInt(10);
                System.out.println("====> " + value);
                // if (value == 2) {
                //     break;
                // }
                try {
                    Thread.sleep(200);
                    queue.put(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("====< " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        provider.start();
        consumer.start();
        provider.join();
        consumer.join();
    }

    @Test
    public void executorsTest() throws InterruptedException {
        // 创建一个包含固定个数的线程池  核心线程数和最大线程数都是5，创建线程个数受控 会出现堆积任务，导致吞吐量会成为瓶颈
        ExecutorService executorService // = Executors.newFixedThreadPool(5);
                = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        Runnable task = () -> {
            System.out.println("===> BIZ " + Thread.currentThread().getName());
        };

        for (int i = 0; i < 10; i++) {
            executorService.execute(task);
        }

        System.out.println("newFixedThreadPool 线程任务开始执行");
        executorService.shutdown();

        // 线程数不冲约束Integer的最大值，内部线程默认的过期时间60秒，适合短期内大量的小任务处理，弊端：线程树不可控
        executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(task);
        }
        System.out.println("newFixedThreadPool 线程任务开始执行");
        executorService.shutdown();
    }

    @Test
    public void threadPoolExecutorDetail() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i != 2) {
                tasks.add(() -> System.out.println("===> BIZ " + Thread.currentThread().getName()));
            } else {
                tasks.add(() -> {
                    if (3 > 1) {
                        throw new RuntimeException("pos");
                    }
                    System.out.println("===> BIZ " + Thread.currentThread().getName());
                });
            }
        }

        for (int i = 0; i < 10; i++) {
            executorService.execute(tasks.get(i));
        }

        System.out.println("newFixedThreadPool 线程任务开始执行");
        executorService.shutdown();
    }


    @Test
    public void retry() {
        breakRetry();
        continueRetry();
    }

    private static void breakRetry() {
        int i = 0;
        retry:
        for (; ; ) {
            System.out.println("start");
            for (; ; ) {
                i++;
                if (i == 4)
                    break retry;
            }
        }
        // start 进入外层循环
        // 4
        System.out.println(i);
    }


    private static void continueRetry() {
        int i = 0;
        retry:
        for(;;) {
            System.out.println("start");
            for(;;) {
                i++;
                if (i == 3)
                    continue retry;
                System.out.println("end");
                if (i == 4)
                    break retry;
            }
        }
        //start 第一次进入外层循环
        //end i=1输出
        //end i=2输出
        //start 再次进入外层循环
        //end i=4输出
        //4 最后输出
        System.out.println(i);
    }

}
