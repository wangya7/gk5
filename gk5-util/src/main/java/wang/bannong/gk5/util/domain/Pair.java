package wang.bannong.gk5.util.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wang.bannong on 2018/7/1 上午1:13
 */
@Data
public class Pair<K, V> implements Serializable {
    private static final long serialVersionUID = 4406609886448761684L;
    private K k;
    private V v;

    public static <K, V> Pair of(K k, V v) {
        Pair pair = new Pair();
        pair.setK(k);
        pair.setV(v);
        return pair;
    }
}
