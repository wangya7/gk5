package wang.bannong.gk5.util;

import com.rits.cloning.Cloner;

/**
 * Created by wang.bannong on 2017/8/4 15:28.
 */
public final class CloneUtil {

    private volatile static Cloner cloner = null;

    public static <T> T deepClone(T t) {
        return getCloner().deepClone(t);
    }

    private static Cloner getCloner() {
        if (null == cloner) {
            synchronized (CloneUtil.class) {
                if (null == cloner) {
                    cloner = new Cloner();
                }
            }
        }
        return cloner;
    }

}
