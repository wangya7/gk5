package wang.bannong.gk5.offer.lld.chain_of_responsibility;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.BasePipelineContext;

/**
 * 定义一个抽象的幂等模版类AbstractStageIdempotent，实现Stage、Idempotent接口，
 * 用于在Stage方法执行的前后加上幂等的校验，其中幂等的实现依赖于TairStringInterface接口。
 *
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/3
 */
@Slf4j
public abstract class AbstractStageIdempotent<T extends BasePipelineContext> implements Idempotent<T>, Stage<T> {

    /**
     * 提供一个用于子类处理业务逻辑的入口
     * @param context 上下文
     * @return 执行结果
     */
    protected abstract T executeBusinessLogic(T context);

    @Override
    public T execute(T context)
    {
        // 拿到当前执行的Stage名称
        String simpleName = this.getClass().getSimpleName();
        String idempotentKey = getIdempotentKey(context);
        // 真实业务场景需要根据业务标识确定
        String key = idempotentKey + ":" + System.nanoTime() + ":" + UUID.randomUUID();
        try {
            // 如果已经处理过，则无需执行业务逻辑，直接跳过当前流程
            if (idempotentKey != null && getMark(key)) {
                log.info(simpleName + " is already processed, the idempotent key:{}", key);
                return context;
            }
            // 执行业务逻辑
            context = executeBusinessLogic(context);
            // 标记为处理过（仅当业务执行成功时）
            if (idempotentKey != null && context.getResult() != null && context.getResult().isSuccess()) {
                if(!marked(key)) {
                    // 执行失败，则抛出异常
                    log.error(simpleName + " marked error, the idempotent key:{}", key);
                    context.setResult(StageProcessedResultEnum.IDEMPOTENT_FAIL);
                }
                log.info(simpleName + " execute success, marked idempotent key:{}", key);
            }
        } catch (Exception e) {
            log.error(simpleName + " execute error, the idempotent key:{}, context:{}", key, e, context);
            context.setResult(StageProcessedResultEnum.IDEMPOTENT_FAIL);
        }
        return context;
    }

    /**
     * 检查是否存在标记值
     * @param key 幂等key
     * @return 是否存在
     */
    private boolean getMark(String key) {
        return TairStringInterface.hasKey(key);
    }

    /**
     * 标记
     * @param key 幂等key
     * @return 标记结果
     */
    private boolean marked(String key) {
        return TairStringInterface.put(key);
    }
}
