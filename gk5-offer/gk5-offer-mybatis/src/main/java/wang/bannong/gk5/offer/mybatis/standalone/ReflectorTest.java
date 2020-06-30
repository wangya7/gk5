package wang.bannong.gk5.offer.mybatis.standalone;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import lombok.Data;
import lombok.ToString;

public class ReflectorTest {

    @Data
    abstract class father<T> {
        private T       id;
        private List<T> child;
    }

    @Data
    @ToString(callSuper = true)
    class son extends father<String> {
        private boolean hasChild;
    }

    @Test
    public void t() throws InvocationTargetException, IllegalAccessException {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        Reflector reflector = reflectorFactory.findForClass(son.class);
        System.out.println(reflector.hasGetter("child"));
        System.out.println(reflector.hasGetter("class"));
        System.out.println(reflector.hasSetter("son"));

        System.out.println(reflector.getGetterType("id").getName());
        System.out.println(reflector.getSetterType("hasChild").getName());
        System.out.println(reflector.getGetterType("child").getName());

        son s = new son();
        reflector.getSetInvoker("id").invoke(s, new Object[]{"Piter"});
        System.out.println(s);
    }
}
