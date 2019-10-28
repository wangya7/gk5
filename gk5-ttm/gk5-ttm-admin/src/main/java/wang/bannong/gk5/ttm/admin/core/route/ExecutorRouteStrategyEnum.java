package wang.bannong.gk5.ttm.admin.core.route;

import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteBusyover;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteConsistentHash;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteFailover;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteFirst;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteLFU;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteLRU;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteLast;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteRandom;
import wang.bannong.gk5.ttm.admin.core.route.strategy.ExecutorRouteRound;
import wang.bannong.gk5.ttm.admin.core.util.I18nUtil;

/**
 * Created by xuxueli on 17/3/10.
 */
public enum ExecutorRouteStrategyEnum {

    FIRST(I18nUtil.getString("jobconf_route_first"), new ExecutorRouteFirst()),
    LAST(I18nUtil.getString("jobconf_route_last"), new ExecutorRouteLast()),
    ROUND(I18nUtil.getString("jobconf_route_round"), new ExecutorRouteRound()),
    RANDOM(I18nUtil.getString("jobconf_route_random"), new ExecutorRouteRandom()),
    CONSISTENT_HASH(I18nUtil.getString("jobconf_route_consistenthash"), new ExecutorRouteConsistentHash()),
    LEAST_FREQUENTLY_USED(I18nUtil.getString("jobconf_route_lfu"), new ExecutorRouteLFU()),
    LEAST_RECENTLY_USED(I18nUtil.getString("jobconf_route_lru"), new ExecutorRouteLRU()),
    FAILOVER(I18nUtil.getString("jobconf_route_failover"), new ExecutorRouteFailover()),
    BUSYOVER(I18nUtil.getString("jobconf_route_busyover"), new ExecutorRouteBusyover()),
    SHARDING_BROADCAST(I18nUtil.getString("jobconf_route_shard"), null);

    ExecutorRouteStrategyEnum(String title, ExecutorRouter router) {
        this.title = title;
        this.router = router;
    }

    private String title;
    private ExecutorRouter router;

    public String getTitle() {
        return title;
    }
    public ExecutorRouter getRouter() {
        return router;
    }

    public static ExecutorRouteStrategyEnum match(String name, ExecutorRouteStrategyEnum defaultItem){
        if (name != null) {
            for (ExecutorRouteStrategyEnum item: ExecutorRouteStrategyEnum.values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
        }
        return defaultItem;
    }

}
