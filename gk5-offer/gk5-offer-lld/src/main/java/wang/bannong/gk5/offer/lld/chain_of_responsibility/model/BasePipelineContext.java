package wang.bannong.gk5.offer.lld.chain_of_responsibility.model;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;
import wang.bannong.gk5.offer.lld.chain_of_responsibility.StageProcessedResultEnum;

/**
 * 定义上下文BasePipelineContext用于Pipeline中传递参数
 */
@Data
public class BasePipelineContext implements Serializable {

    /**
     * 扩展信息
     */
    private Map<String, String> extInfo;

    /**
     * 处理结果
     */
    private StageProcessedResultEnum result;

}
