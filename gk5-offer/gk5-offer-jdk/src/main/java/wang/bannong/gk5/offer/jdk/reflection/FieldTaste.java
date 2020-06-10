package wang.bannong.gk5.offer.jdk.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class FieldTaste {

    /**
     * 返回所有的public字段，包括其父类的，如果没有字段，返回空数组
     * public Field[] getFields()
     *
     * 返回本类声明的所有字段，包括非public的，但不包括父类的
     * public Field[] getDeclaredFields()
     *
     * 返回本类或父类中指定名称的public字段，找不到抛出异常NoSuchFieldException
     * public Field getField(String name)
     *
     * 返回本类中声明的指定名称的字段，找不到抛出异常NoSuchFieldException
     * public Field getDeclaredField(String name)
     *
     */
    @Test
    public void t01() {
        NeoClean record = new NeoClean();
        record.setColor("PINK");
        record.setPeriods(new int[]{1,7});
        record.setPrice(34);
        System.out.println(record);

        Class<?> clazz = record.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.getName().equals("color")) {
                    field.setAccessible(true);
                    try {
                        field.set(record, "RED");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(record);


        Field f = null;
        try {
            f = record.getClass().getField("MAX_NAME_LEN");
            int mod = f.getModifiers();
            System.out.println(Modifier.toString(mod));
            System.out.println("isPublic:   " + Modifier.isPublic(mod));
            System.out.println("isStatic:   " + Modifier.isStatic(mod));
            System.out.println("isFinal:    " + Modifier.isFinal(mod));
            System.out.println("isVolatile: " + Modifier.isVolatile(mod));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        List<String> obj = Arrays.asList(new String[]{"老马","编程"});
        Class<?> cls = obj.getClass();
        for(Field field : cls.getDeclaredFields()){
            field.setAccessible(true);
            try {
                System.out.println(field.getName()+" - "+field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
