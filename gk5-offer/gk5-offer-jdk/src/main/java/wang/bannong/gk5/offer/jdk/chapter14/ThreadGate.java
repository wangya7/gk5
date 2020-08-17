package wang.bannong.gk5.offer.jdk.chapter14;

/**
 * 阀门 二元闭锁
 */
public class ThreadGate {
    private boolean isOpen;
    private long    generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        long generationOld = generation;
        /***
         * 为什么增加generation字段，为什么generation需要作为条件谓词判断？
         * 如果阀门打开以后又快速的关闭啦，导致一部门的线程还没有来得及释放接着wait啦，这将导致
         * 这部分线程永远得到CPU的运行权限，饿死啦！！！
         */
        while (!isOpen && generationOld == generation) {
            wait();
        }
    }

}
