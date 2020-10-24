package wang.bannong.gk5.util.model;

import java.io.Serializable;

/**
 * Created by wang.bannong on 2018/7/1 上午1:13
 */
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

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
