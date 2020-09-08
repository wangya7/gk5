package wang.bannong.gk5.offer.jdk.concurrent.cases;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class Case_Unsafe {

    private final static Unsafe unsafe = reflectGetUnsafe();
    private volatile     int    value;
    private final static long   valueOffset;

    /**
     * 绝对不能像AtomicInteger那样子使用
     * private static final Unsafe unsafe = Unsafe.getUnsafe();
     * 因为AtomicInteger默认的加载器是Bootstrap加载，而我们自己编写的Class是由AppClassLoader加载，导致在
     * Unsafe.getUnsafe()内部会报错。
     * 1. 把调用 Unsafe 相关方法的类所在jar包路径追加到默认的bootstrap路径中，通过-Xbootclasspath
     * 2. 通过反射获取单例对象 theUnsafe
     *
     * @return
     */
    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (Case_Unsafe.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public Case_Unsafe(int value) {
        this.value = value;
    }

    public Case_Unsafe() {
        this.value = 0;
    }


    public final int getValue() {
        return value;
    }

    public final void setValue(int value) {
        this.value = value;
    }

    public static long getValueOffset() {
        return valueOffset;
    }

    // private static final Executor exec = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        int tmp = count.incrementAndGet();
        System.out.println("count=" + count);
        count.addAndGet(23);
        System.out.println("count=" + count);
        count.compareAndSet(24, 42);
        System.out.println("count=" + count);

        Case_Unsafe case_unsafe = new Case_Unsafe(390);
        System.out.println(System.identityHashCode(case_unsafe));
        System.out.println(case_unsafe.getValueOffset());
    }
}
