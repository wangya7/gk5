package wang.bannong.gk5.offer.jdk.concurrent;

import java.util.concurrent.Callable;

public class MyExecuteThread<R> extends Thread {

    private R result = null;
    private Exception exception = null;
    private boolean done = false;
    private Callable<R> task;
    private Object lock;

    public MyExecuteThread(Callable<R> task, Object lock) {
        this.task = task;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            result = task.call();
        } catch (Exception e) {
            exception = e;
        } finally {
            synchronized (lock) {
                done = true;
                lock.notifyAll();
            }
        }
    }

    public R getResult() {
        return result;
    }

    public boolean isDone() {
        return done;
    }

    public Exception getException() {
        return exception;
    }
}
