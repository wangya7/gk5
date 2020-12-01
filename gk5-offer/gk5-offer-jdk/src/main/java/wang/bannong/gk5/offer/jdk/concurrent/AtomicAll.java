package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

public class AtomicAll {

    @Test
    public void jj()  {
        String a = "helloworld2";      // 常量池创建"helloworld2" a直接指向常量池
        final String b = "helloworld"; // b定义成final 那么下文引用b的地方在编译期间全部直接使用"helloworld" 替换
        String c = "helloworld";
        // System.out.println(b == c);   true

        String d = b + 2;              // d = "helloworld" + 2; 即 d = "helloworld2";
        String e = c + 2;
        System.out.println((a == d));
        System.out.println((a == e));


        String s1 = "d3f43g45";
        String s2 = new String("d3f43g45");
        System.out.println(s1 == s2);
    }


    /**
     * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
     */
    @Test
    public void testJdk8_jdk7() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);   // java8 false; java7 false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);  // java8 false; java7 true
    }


    @Test
    public void longAdderTest() throws InterruptedException {
        LongAdder count = new LongAdder();
        System.out.println(count.longValue());
        System.out.println(count.sum());
        // count.increment();
        // count.add(3);
        System.out.println(count.longValue());
        System.out.println(count.sum());

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; ++i) {
            threads[i] = new Thread(() -> count.increment());
        }
        for (int i = 0; i < 20; ++i) {
            threads[i].start();
            threads[i].join();
        }

        System.out.println(count.longValue());
        System.out.println(count.sum());
    }

    @Test
    public void array() {
        int temvalue = 0;
        int[] nums = {1, 2, 3, 4, 5, 6};
        AtomicIntegerArray i = new AtomicIntegerArray(nums);
        for (int j = 0; j < nums.length; j++) {
            System.out.println(i.get(j));
        }
        temvalue = i.getAndSet(0, 2);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
        temvalue = i.getAndIncrement(0);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
        temvalue = i.getAndAdd(0, 5);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
    }

    @Test
    public void atomicStampedReferenceTest() {
        // 实例化、取当前值和 stamp 值
        final Integer initialRef = 0, initialStamp = 0;
        final AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(initialRef, initialStamp);
        System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp());

        // compare and set
        final Integer newReference = 666, newStamp = 999;
        final boolean casResult = asr.compareAndSet(initialRef, newReference, initialStamp, newStamp);
        System.out.println("currentValue=" + asr.getReference()
                + ", currentStamp=" + asr.getStamp()
                + ", casResult=" + casResult);

        // 获取当前的值和当前的 stamp 值
        int[] arr = new int[1];
        final Integer currentValue = asr.get(arr);
        final int currentStamp = arr[0];
        System.out.println("currentValue=" + currentValue + ", currentStamp=" + currentStamp);

        // 单独设置 stamp 值
        final boolean attemptStampResult = asr.attemptStamp(newReference, 88);
        System.out.println("currentValue=" + asr.getReference()
                + ", currentStamp=" + asr.getStamp()
                + ", attemptStampResult=" + attemptStampResult);

        // 重新设置当前值和 stamp 值
        asr.set(initialRef, initialStamp);
        System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp());

        // [不推荐使用，除非搞清楚注释的意思了] weak compare and set
        // 困惑！weakCompareAndSet 这个方法最终还是调用 compareAndSet 方法。[版本: jdk-8u191]
        // 但是注释上写着 "May fail spuriously and does not provide ordering guarantees,
        // so is only rarely an appropriate alternative to compareAndSet."
        // todo 感觉有可能是 jvm 通过方法名在 native 方法里面做了转发
        final boolean wCasResult = asr.weakCompareAndSet(initialRef, newReference, initialStamp, newStamp);
        System.out.println("currentValue=" + asr.getReference()
                + ", currentStamp=" + asr.getStamp()
                + ", wCasResult=" + wCasResult);
    }

}
