package wang.bannong.gk5.offer.lld.chain_of_responsibility.stage;


import wang.bannong.gk5.offer.lld.chain_of_responsibility.AbstractStageIdempotent;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.StageConfig;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.StageProcessedResultEnum;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.IdleUserRightsCardDTO;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.IdleUserRightsContext;

@StageConfig(name = "CreateCardHistory")
public class CreateCardHistory extends AbstractStageIdempotent<IdleUserRightsContext> {

    /**
     * 提供一个用于子类处理业务逻辑的入口
     *
     * @param context 上下文
     * @return 执行结果
     */
    @Override
    protected IdleUserRightsContext executeBusinessLogic(IdleUserRightsContext context) {
        IdleUserRightsCardDTO card = context.getCard();
        Long historyId = 73L;
        return context.ofResult(historyId == null ?
            StageProcessedResultEnum.WRITE_USER_RIGHTS_FLOW_DB_FAIL :
            StageProcessedResultEnum.SUCCESS);
    }

    /**
     * 获取幂等key，返回null代表不需要幂等
     *
     * @param context 上下文
     * @return 幂等key
     */
    @Override
    public String getIdempotentKey(IdleUserRightsContext context) {
        IdleUserRightsCardDTO card = context.getCard();
        // 幂等key：idle-local-CreateCardHistory-{bizCode}-{userId}-{cardId}
        return "CreateCardHistory";
    }
}
