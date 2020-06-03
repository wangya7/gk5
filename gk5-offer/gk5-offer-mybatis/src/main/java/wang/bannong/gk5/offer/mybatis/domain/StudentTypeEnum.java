package wang.bannong.gk5.offer.mybatis.domain;

import java.util.HashMap;
import java.util.Map;

public enum StudentTypeEnum {
    common((byte) 1),
    special((byte) 2),
    ;
    private final byte code;

    private static Map<Byte, StudentTypeEnum> map = new HashMap<>();

    static {
        for (StudentTypeEnum item : StudentTypeEnum.values()) {
            map.put(item.getCode(), item);
        }
    }

    StudentTypeEnum(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static StudentTypeEnum of(byte code) {
        return map.get(code);
    }

}
