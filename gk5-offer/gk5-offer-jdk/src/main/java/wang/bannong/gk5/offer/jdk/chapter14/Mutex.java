package wang.bannong.gk5.offer.jdk.chapter14;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 不可重入的互斥锁 0-解锁状态 1-锁定状态
 */
public class Mutex implements Lock, java.io.Serializable {
    private static final long serialVersionUID = 2549474520930063539L;

    private final Synch synch = new Synch();

    @Override
    public void lock() {
        synch.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        synch.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return synch.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return synch.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        synch.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return synch.newCondition();
    }

    public boolean isLocked() {
        return synch.isHeldExclusively();
    }


    private static class Synch extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 7455959810054775614L;

        // 是否是锁定状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 获取一个锁
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 创建一个Condition
        Condition newCondition() { return new ConditionObject(); }

        // 正确的反序列化
        private void readObject(ObjectInputStream s)
                throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }


    public static void main(String[] args) {

    }
}
