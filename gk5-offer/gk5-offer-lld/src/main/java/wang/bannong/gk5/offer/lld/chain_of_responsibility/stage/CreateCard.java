package wang.bannong.gk5.offer.lld.chain_of_responsibility.stage;

import wang.bannong.gk5.offer.lld.chain_of_responsibility.AbstractStageIdempotent;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.StageConfig;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.StageProcessedResultEnum;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.IdleUserRightsCardDTO;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.IdleUserRightsContext;

@StageConfig(name = "CreateCard")
public class CreateCard extends AbstractStageIdempotent<IdleUserRightsContext> {

    /**
     * 提供一个用于子类处理业务逻辑的入口
     *
     * @param context 上下文
     * @return 执行结果
     */
    @Override
    protected IdleUserRightsContext executeBusinessLogic(IdleUserRightsContext context) {
        IdleUserRightsCardDTO cardDTO = context.getCard();
        // 写权益卡记录
        System.out.println("模拟执行特殊的业务");
        Long cardId = 21L;
        // 写权益卡id字段，用于下游使用
        cardDTO.setCardId(cardId);
        return context.ofResult(cardId == null ?
            StageProcessedResultEnum.WRITE_USER_RIGHTS_RECORD_DB_FAIL :
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
        // 幂等key：idle-local-CreateCard-{bizCode}-{userId}-{ppPayId}
        return "CreateCard";
    }
}