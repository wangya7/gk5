package wang.bannong.gk5.offer.lld.chain_of_responsibility;

import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.BasePipelineContext;

/**
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/3
 */
public interface Idempotent<T extends BasePipelineContext> {
    /**
     * 获取幂等key，返回null代表不需要幂等
     * @param context 上下文
     * @return 幂等key
     */
    String getIdempotentKey(T context);
}
