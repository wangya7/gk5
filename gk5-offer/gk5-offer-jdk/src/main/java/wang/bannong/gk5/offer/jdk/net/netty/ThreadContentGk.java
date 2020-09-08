package wang.bannong.gk5.offer.jdk.net.netty;

import org.junit.Test;

public class ThreadContentGk {


    @Test
    public void r() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2
    }



}
