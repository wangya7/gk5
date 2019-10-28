package wang.bannong.gk5.ttm.admin.core.route.strategy;

import java.util.List;

import wang.bannong.gk5.ttm.admin.core.route.ExecutorRouter;
import wang.bannong.gk5.ttm.core.biz.model.ReturnT;
import wang.bannong.gk5.ttm.core.biz.model.TriggerParam;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
