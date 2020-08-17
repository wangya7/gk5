package wang.bannong.gk5.offer.jdk.concurrent.demo1;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    /**
     * 模拟多任务执行，返回执行消耗毫秒时间
     * 不使用线程池，直接创建Thread
     *
     * @param threadCount 线程数
     * @param task        任务
     */
    public static long timesOneTask(int threadCount, Runnable task) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    start.await();
                    try {
                        task.run();
                    } finally {
                        end.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        long beginTime = System.currentTimeMillis();
        start.countDown();
        end.await();
        long endTime = System.currentTimeMillis();
        return endTime - beginTime;
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(TestHarness.timesOneTask(100, () -> {
            long count = 0;
            for (int i = 0; i < 100000000; i++) {
                count = count + (i + 1);
            }
        }));
    }
}
