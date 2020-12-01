package wang.bannong.gk5.offer.jdk.concurrent;

import java.util.Random;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyLock {

    private Syn syn = new Syn();

    private class Syn extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 6322706395337132202L;

        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    public void lock() {
        syn.acquire(1);
    }

    public void unlock() {
        syn.release(1);
    }


    public static void main(String[] args) {
        MyLock lock = new MyLock();
        Random random = new Random();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获得锁");
                    Thread.sleep(random.nextInt(500));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 释放锁");
                    lock.unlock();
                }

            }
        };

        int size = 50;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < 50; ++i) {
            threads[i] = new Thread(runnable, "T-" + (i + 1));
        }

        for (int i = size; 0 < i; --i) {
            threads[i - 1].start();
        }

        for (int i = 0; i < 50; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
