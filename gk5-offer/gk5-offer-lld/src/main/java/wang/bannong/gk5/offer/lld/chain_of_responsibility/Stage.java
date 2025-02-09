package wang.bannong.gk5.offer.lld.chain_of_responsibility;

import wang.bannong.gk5.offer.lld.chain_of_responsibility.model.BasePipelineContext;

/**
 * 定义Pipeline中的基本方法类为Stage接口，其中泛型T继承自BasePipelineContext类
 *
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/3
 */
public interface Stage<T extends BasePipelineContext> {

    /**
     * 执行当前阶段的逻辑
     */
    T execute(T context);
}
