package wang.bannong.gk5.ntm.common.util;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

public class BeanUtils {

    public static <S, T> T copy(S source, Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        copyProperties(source, t);
        return t;
    }

    public static <S, T> List<T> copyList(List<S> source, Class<T> clazz) throws Exception {
        List<T> tList = new ArrayList<>();
        for (S s : source) {
            T t = clazz.newInstance();
            copyProperties(s, t);
            tList.add(t);
        }
        return tList;
    }

}