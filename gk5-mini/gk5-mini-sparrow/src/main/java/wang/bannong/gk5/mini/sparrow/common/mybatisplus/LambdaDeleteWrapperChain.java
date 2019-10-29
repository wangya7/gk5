package wang.bannong.gk5.mini.sparrow.common.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.additional.AbstractChainWrapper;

import java.util.function.Predicate;

import wang.bannong.gk5.mini.sparrow.framework.service.BaseService;

/**
 * Lambda删除Wrapper
 */
public class LambdaDeleteWrapperChain<T> extends AbstractChainWrapper<T, SFunction<T, ?>, LambdaDeleteWrapperChain<T>, LambdaQueryWrapper<T>>
        implements Query<LambdaDeleteWrapperChain<T>, T, SFunction<T, ?>> {
    private static final long           serialVersionUID = 1L;
    private              BaseService<T> baseService;

    public LambdaDeleteWrapperChain(BaseService<T> baseService) {
        super();
        this.baseService = baseService;
        super.wrapperChildren = new LambdaQueryWrapper<>();
    }

    @SafeVarargs
    @Override
    public final LambdaDeleteWrapperChain<T> select(SFunction<T, ?>... columns) {
        wrapperChildren.select(columns);
        return typedThis;
    }

    @Override
    public LambdaDeleteWrapperChain<T> select(Predicate<TableFieldInfo> predicate) {
        wrapperChildren.select(predicate);
        return typedThis;
    }

    @Override
    public LambdaDeleteWrapperChain<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        wrapperChildren.select(entityClass, predicate);
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        throw ExceptionUtils.mpe("can not use this method for \"%s\"", "getSqlSelect");
    }

    public boolean execute() {
        return baseService.remove(getWrapper());
    }

}