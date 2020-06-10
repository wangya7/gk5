package wang.bannong.gk5.offer.jdk.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodTaste {

    /**
     * 返回所有的public方法，包括其父类的，如果没有方法，返回空数组
     * public Method[] getMethods()
     * <p>
     * 返回本类声明的所有方法，包括非public的，但不包括父类的
     * public Method[] getDeclaredMethods()
     * <p>
     * 返回本类或父类中指定名称和参数类型的public方法，找不到抛出异常NoSuchMethodException
     * public Method getMethod(String name, Class<?>... parameterTypes)
     * <p>
     * 返回本类中声明的指定名称和参数类型的方法，找不到抛出异常NoSuchMethodException
     * public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
     * <p>
     * 获取方法的名称
     * public String getName()
     * <p>
     * flag设为true表示忽略Java的访问检查机制，以允许调用非public的方法
     * public void setAccessible(boolean flag)
     * <p>
     * 在指定对象obj上调用Method代表的方法，传递的参数列表为args
     * public Object invoke(Object obj, Object... args) throws IllegalAccessException,
     * IllegalArgumentException, InvocationTargetException
     */

    @Test
    public void t01() {
        Integer i = 12;
        Class<?> cls = i.getClass();
        try {
            Method method = cls.getMethod("valueOf", new Class[]{String.class});
            Object object = method.invoke(null, "123");
            System.out.println(object);
            System.out.println(i);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建对象和构造方法
     */
    @Test
    public void t02() {
        Map<String, Integer> map = null;
        try {
            map = HashMap.class.newInstance();
            map.put("hello", 123);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(map);


        Constructor<StringBuilder> contructor = null;
        try {
            contructor = StringBuilder.class.getConstructor(new Class[]{String.class});
            StringBuilder sb = contructor.newInstance("HB");
            sb.append("_BN");
            System.out.println(sb.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 类型检查和转换
     */
    @Test
    public void t03() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5));

        // 用来判断变量指向的实际对象类型
        if (list instanceof ArrayList) {
            System.out.println("list ping");
        }

        Class<?> clazz = null;
        try {
            clazz = Class.forName("java.util.ArrayList");

            // 这个和上面的判断含义是一样的
            if (clazz.isInstance(list)) {
                System.out.println("list ping");
                // 强制转换
                ArrayList<Integer> arrayList1 = (ArrayList<Integer>) list;
                System.out.println(arrayList1);

                // 强制转换 其实 class.cast里面还是会做类型判断
                ArrayList<Integer> arrayList2 = (ArrayList<Integer>) clazz.cast(list);
                System.out.println(arrayList2);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 上面的 instanceof 和 cast 都是阐述 类和对象的关系，Class还有一个方法，可以判断Class之间的关系
        // 检查参数类型cls能否赋给当前Class类型的变量
        // public native boolean isAssignableFrom(Class<?> cls);

        System.out.println(Object.class.isAssignableFrom(String.class));
        System.out.println(String.class.isAssignableFrom(String.class));
        System.out.println(List.class.isAssignableFrom(ArrayList.class));
        System.out.println(ArrayList.class.isAssignableFrom(List.class));           // false

        System.out.println(FatherNeoClean.class.isAssignableFrom(NeoClean.class));  // false
        System.out.println(NeoClean.class.isAssignableFrom(FatherNeoClean.class));
    }
}
