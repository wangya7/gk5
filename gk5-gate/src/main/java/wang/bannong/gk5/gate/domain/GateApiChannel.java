package wang.bannong.gk5.gate.domain;

/**
 * Created by wang.bannong on 2018/5/14 下午7:07
 */
public enum GateApiChannel {
    app("app"),     // app-c端业务
    appb("appb"),   // app-b端业务
    admin("admin"), // 后台业务
    p3rd("p3rd")    // 外部接口
    ;

    private String desc;

    GateApiChannel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static GateApiChannel of(String value) {
        for (GateApiChannel item : values()) {
            if (item.name().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
