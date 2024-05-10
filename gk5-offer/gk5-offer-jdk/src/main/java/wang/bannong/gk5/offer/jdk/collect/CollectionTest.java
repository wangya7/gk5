package wang.bannong.gk5.offer.jdk.collect;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CollectionTest {


    @Test
    public void reserve() {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 4, 6, 2, 8));

    }


    @Test
    public void oo() {
        PriorityQueue<Task> tasks = new PriorityQueue(11, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.priority > o2.priority) {
                    return -1;
                } else if (o1.priority < o2.priority) {
                    return 1;
                }

                return 0;
            }
        });

        tasks.offer(new Task(20, "写日记"));
        tasks.offer(new Task(10, "看电视"));
        tasks.offer(new Task(100, "写代码"));

        while (tasks.peek() != null) {
            System.out.println(tasks.poll());
        }
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
}
