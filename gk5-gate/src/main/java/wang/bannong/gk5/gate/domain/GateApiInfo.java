package wang.bannong.gk5.gate.domain;

import lombok.Data;
import wang.bannong.gk5.util.Constant;

import java.util.Objects;

/**
 * Created by wang.bannong on 2018/5/13 下午9:24
 */
@Data
public class GateApiInfo {

    final static String ERROR_MSG = "gate-api.yml config format error";

    private GateApiChannel channel;
    private String         name;
    private String         version;
    private String         bean;
    private boolean        needLogin;
    private String         method;

    public static GateApiInfo of(String apiKey, String apiValue) {
        Objects.requireNonNull(apiKey, ERROR_MSG);
        Objects.requireNonNull(apiValue, ERROR_MSG);

        GateApiInfo info = new GateApiInfo();
        String[] arr1 = apiKey.split(Constant.UNDERLINE);
        if (arr1.length != 2) throw new RuntimeException(ERROR_MSG);
        info.setName(arr1[0].trim());
        info.setVersion(arr1[1].trim());

        String[] arr2 = apiValue.split(Constant.COMMA);
        if (arr2.length != 3) throw new RuntimeException(ERROR_MSG);
        info.setBean(arr2[0].trim());
        info.setNeedLogin(Integer.parseInt(arr2[1]) > 0);
        info.setMethod(arr2[2].trim());
        return info;
    }

}
