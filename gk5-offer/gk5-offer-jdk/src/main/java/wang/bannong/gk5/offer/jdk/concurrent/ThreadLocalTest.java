package wang.bannong.gk5.offer.jdk.concurrent;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.CountDownLatch;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    @Test
    public void main() throws InterruptedException {
        U task = new U();
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
    }


    static class U implements Runnable {
        private ThreadLocal<String>  threadLocal1 = new ThreadLocal<>();
        private ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        @Override
        public void run() {
            threadLocal1.set(Thread.currentThread().getName());
            threadLocal2.set(new Random().nextInt());
            System.out.println(threadLocal1.get() + "\t" + threadLocal2.get());
        }
    }

    @Test
    public void inheritableThreadLocalTest1() throws InterruptedException {
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("Rust");
        System.out.println(threadLocal.get());

        new Thread(() -> System.out.println(threadLocal.get())).start();
        Thread.sleep(500);
    }

    /**
     * 输出结果：
     * pool-1-thread-1__--->哈哈哈
     * pool-1-thread-1__--->哈哈哈
     */
    @Test
    public void inheritableThreadLocalTest2() throws InterruptedException {
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("Rust");
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + ":" + threadLocal.get());
        });
        threadLocal.set("Java");

        executorService.execute(() -> {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + ":" + threadLocal.get());
        });
        System.out.println("Main thread:" +threadLocal.get());

        executorService.shutdown();
    }

    //**************** TransmittableThreadLocal
    @Test
    public void thl1() throws InterruptedException {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("Rust");

        Thread thread = new Thread(() -> {
            System.out.println(context.get());
        });
        thread.start();
        Thread.sleep(500);
    }

    @Test
    public void thl2() throws InterruptedException {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("Rust");
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable task = () -> {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "__" + context.get());
        };
        Runnable ttlRunnable = TtlRunnable.get(task);
        executorService.execute(ttlRunnable);
        //重新修改InheritableThreadLocal内的数据
        context.set("Java");

        executorService.execute(ttlRunnable);

        executorService.shutdown();
    }
}
