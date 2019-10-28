package wang.bannong.gk5.ttm.admin.core.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import wang.bannong.gk5.ttm.core.biz.model.ReturnT;
import wang.bannong.gk5.ttm.core.biz.model.TriggerParam;

/**
 * Created by xuxueli on 17/3/10.
 */
public abstract class ExecutorRouter {
    protected static Logger logger = LoggerFactory.getLogger(ExecutorRouter.class);

    /**
     * route address
     *
     * @param addressList
     * @return  ReturnT.content=address
     */
    public abstract ReturnT<String> route(TriggerParam triggerParam, List<String> addressList);

}
