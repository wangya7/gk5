package wang.bannong.gk5.ttm.core.biz;

import java.util.List;

import wang.bannong.gk5.ttm.core.biz.model.HandleCallbackParam;
import wang.bannong.gk5.ttm.core.biz.model.RegistryParam;
import wang.bannong.gk5.ttm.core.biz.model.ReturnT;

/**
 * @author xuxueli 2017-07-27 21:52:49
 */
public interface AdminBiz {

    String MAPPING = "/api";


    // ---------------------- callback ----------------------

    /**
     * callback
     *
     * @param callbackParamList
     * @return
     */
    ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);


    // ---------------------- registry ----------------------

    /**
     * registry
     *
     * @param registryParam
     * @return
     */
    ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam
     * @return
     */
    ReturnT<String> registryRemove(RegistryParam registryParam);

}
