package wang.bannong.gk5.offer.jdk.collect;

import java.util.HashMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CollectionTest {


    @Test
    public void reserve() {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 4, 6, 2, 8));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Task {
        private int    priority;
        private String name;
    }

    private enum Stage {
        eff, exp
    }

    @Test
    public void enumMapTest() {
        Map m = new EnumMap(Stage.class);
        m.put(Stage.eff, "HH");
        m.put(Stage.exp, 45);
        System.out.println(m);
    }

    @Test
    public void mapTreeify() {
        HashMap<Integer, String> map = new HashMap<>(1,1f);
        for (int i = 1; i <=10; i++) {
            map.put(i, String.valueOf(i));
        }
    }
}
