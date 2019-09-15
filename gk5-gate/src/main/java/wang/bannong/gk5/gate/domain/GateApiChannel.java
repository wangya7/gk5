package wang.bannong.gk5.gate.domain;

/**
 * Created by wang.bannong on 2018/5/14 下午7:07
 */
public enum GateApiChannel {
    app("app"),
    admin("admin"),
    p3rd("p3rd")
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
