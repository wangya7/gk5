package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    @Test
    public void demo() {
        System.out.println("before");
        LockSupport.park(this);
        System.out.println("after");
    }

    @Test
    public void demoMap() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        System.out.println(map);
    }
}
