package wang.bannong.gk5.offer.lld.chain_of_responsibility.model;

import lombok.Data;
import lombok.ToString;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.StageProcessedResultEnum;

/**
 * 定义业务自己的上下文类Context，业务的Stage方法类只需要继承自AbstractStageIdempotent，
 * 泛型参数T继承自BasePipelineContext的任何类即可，这里以我们自定义的一个IdleUserRightsContext
 * （继承自BasePipelineContext）为例
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/3
 */
@Data
@ToString(callSuper = true)
public class IdleUserRightsContext extends BasePipelineContext {
    /**
     * 权益卡
     */
    private IdleUserRightsCardDTO card;

    /**
     * 构建返回结果
     */
    public IdleUserRightsContext ofResult(StageProcessedResultEnum result) {
        super.setResult(result);
        return this;
    }
}
