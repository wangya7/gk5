package wang.bannong.gk5.offer.lld.chain_of_responsibility;

import java.util.HashSet;
import java.util.Set;

/**
 * 【RDB】用于幂等缓存的实现
 *
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/3
 */
public final class TairStringInterface {

    private volatile static Set<String> hub = new HashSet<>();

    public synchronized static boolean hasKey(String key) {
        return hub.contains(key);
    }
    public synchronized static boolean put(String key) {
        return hub.add(key);
    }
}
