package wang.bannong.gk5.offer.jdk.reflection.hotfix;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    private final static String BASE_DIR = "/Users/wangya/backend/code-repository/github/gk5/gk5-offer/gk5-offer-jdk/src/main/resources/classloader/";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.replaceAll("\\.", "/");
        fileName = BASE_DIR + fileName + ".class";
        try {
            InputStream is = new FileInputStream(fileName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }

            byte[] bytes = output.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("failed to load class " + name, e);
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader cl1 = new MyClassLoader();
        String className = "wang.bannong.gk5.offer.jdk.reflection.hotfix.HelloService";
        Class<?> class1 = cl1.loadClass(className);
        IHelloService helloService = (IHelloService) class1.newInstance();
        helloService.sayHello();

        MyClassLoader cl2 = new MyClassLoader();
        Class<?> class2 = cl2.loadClass(className);

        if (class1 != class2) {
            System.out.println("different classes");
        }
    }
}
