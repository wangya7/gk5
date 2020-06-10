package wang.bannong.gk5.offer.jdk.reflection.proxy;

public class ServiceImpl implements IService {
    @Override
    public void sayHi() {
        System.out.println("Hi!");
    }

    @Override
    public void sayBye() {
        System.out.println("Bye!");
    }
}
