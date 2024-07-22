package wang.bannong.gk5.offer.jdk.concurrent;

import java.util.concurrent.Callable;

public class MyExecutor<R> {

    public <R> MyFuture<R> execute(final Callable<R> task) {
        final Object lock = new Object();
        // 封装成一个子进程
        final MyExecuteThread<R> thread = new MyExecuteThread<>(task, lock);
        thread.start();

        MyFuture<R> future = new MyFuture<R>() {
            @Override
            public R get() throws Exception {
                synchronized (lock) {
                    while (!thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    if (thread.getException() != null) {
                        throw thread.getException();
                    }
                    return thread.getResult();
                }
            }
        };
        return future;
    }
}
