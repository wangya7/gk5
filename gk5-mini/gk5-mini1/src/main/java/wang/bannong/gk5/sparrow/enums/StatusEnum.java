package wang.bannong.gk5.sparrow.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import wang.bannong.gk5.sparrow.framework.enums.IEnum;

/**
 * 状态枚举
 */
public enum StatusEnum implements IEnum {

    NORMAL(1), DISABLE(0);

    @EnumValue
    private final int value;

    StatusEnum(final int value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public int getValue() {
        return this.value;
    }
}
