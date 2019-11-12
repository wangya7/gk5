package wang.bannong.gk5.gate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.Getter;
import wang.bannong.gk5.gate.domain.GateApiChannel;
import wang.bannong.gk5.gate.domain.GateApiInfo;

import javax.annotation.PostConstruct;

import java.util.*;

/**
 * Created by wang.bannong on 2018/5/14 下午7:01
 */
@Component
@EnableConfigurationProperties(ApiYmlConfig.class)
public class ApiYmlUtil {

    @Autowired
    private        ApiYmlConfig                          apiYmlConfig;
    @Getter
    private static Map<String, GateApiInfo>              allApiInfo    = new HashMap<>();
    @Getter
    private static Set<String>                           allApiInfoSet = new HashSet<>();
    @Getter
    private static Map<GateApiChannel, Set<GateApiInfo>> apiSetsMap    = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Map<String, String>> map = apiYmlConfig.getApiMap();
        if (!CollectionUtils.isEmpty(map)) {
            Set<Map.Entry<String, Map<String, String>>> outSets = map.entrySet();
            for (Map.Entry<String, Map<String, String>> outEntry : outSets) {
                Set<GateApiInfo> setTmp = new HashSet<>();
                Map<String, String> innner = outEntry.getValue();
                String channel = outEntry.getKey();
                GateApiChannel gateApiChannel = GateApiChannel.of(channel);
                if (!CollectionUtils.isEmpty(innner)) {
                    Set<Map.Entry<String, String>> inSets = innner.entrySet();
                    for (Map.Entry<String, String> innerEntry : inSets) {
                        GateApiInfo mtopApiInfo = GateApiInfo.of(innerEntry.getKey(), innerEntry.getValue());
                        mtopApiInfo.setChannel(gateApiChannel);

                        setTmp.add(mtopApiInfo);
                        allApiInfo.put(mtopApiInfo.getName(), mtopApiInfo);
                        allApiInfoSet.add(mtopApiInfo.getName());
                    }
                }
                apiSetsMap.put(gateApiChannel, setTmp);
            }
        }
    }

    public static GateApiInfo getGateApiInfo(final String apiName) {
        Objects.requireNonNull(apiName, "api name cannot be null");
        return allApiInfo.get(apiName);
    }

    public static boolean isContain(final String apiName) {
        return !CollectionUtils.isEmpty(allApiInfoSet) ? allApiInfoSet.contains(apiName) : false;
    }

}
