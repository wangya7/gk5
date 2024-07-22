package wang.bannong.gk5.offer.jdk.concurrent;

public class DCLDemo {
    /**
     * 双重检查加锁DCL错误的关键：当在没有同步保证的情况下读取一个共享对象时，可能发生最糟糕事情看到一个失效
     * 值（可能是空值），此时DCL方法将通过在持有锁情况再次来避免这种风险。然后这样子情况会变得更加糟糕——线程
     * 可能看到引用的当前值，但对象的状态值却是无效的，这意味着线程可以看到对象处于无效或者错误的状态。
     */
    private volatile static Resource resource1;

    public static Resource getResource1() {
        if (resource1 == null) {
            synchronized (DCLDemo.class) {
                if (resource1 == null) {
                    resource1 = new Resource();
                }
            }
        }
        return resource1;
    }

    /**
     * 推荐使用延迟初始化占位类模式
     */

    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource2() {
        return ResourceHolder.resource;
    }
}

class Resource {
}
