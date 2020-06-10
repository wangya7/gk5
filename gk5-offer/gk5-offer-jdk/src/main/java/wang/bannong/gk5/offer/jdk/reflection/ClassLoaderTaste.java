package wang.bannong.gk5.offer.jdk.reflection;

import org.junit.Test;

public class ClassLoaderTaste {

    @Test
    public void t01() {
        ClassLoader cl = ClassLoaderTaste.class.getClassLoader();
        while (cl != null) {
            System.out.println(cl.getClass().getName());
            cl = cl.getParent();
        }

        System.out.println("===");
        System.out.println(String.class.getClassLoader());
    }


    /**
     * 需要说明的是，由于委派机制，Class的getClassLoader()方法返回的不一定是调用loadClass的ClassLoader，
     * 比如，上面代码中，java.util.ArrayList实际由BootStrap ClassLoader加载，所以返回值就是null。
     */
    @Test
    public void t02() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        System.out.println(cl.getClass().getName());
        try {
            Class<?> cls = cl.loadClass("java.util.ArrayList");
            ClassLoader actualLoader = cls.getClassLoader();
            System.out.println(actualLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
