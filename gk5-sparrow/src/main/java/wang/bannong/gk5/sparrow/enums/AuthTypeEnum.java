package wang.bannong.gk5.sparrow.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import wang.bannong.gk5.sparrow.common.exception.UnknownEnumException;
import wang.bannong.gk5.sparrow.framework.enums.IEnum;


/**
 * 权限类型枚举
 */
public enum AuthTypeEnum implements IEnum {

    /**
     * 需要登录
     */
    LOGIN(1),
    /**
     * 开放,无需鉴权
     */
    OPEN(2),
    /**
     * 需要鉴定是否包含权限
     */
    AUTH(3);

    @EnumValue
    private final int value;

    AuthTypeEnum(final int value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public int getValue() {
        return this.value;
    }

    @JsonCreator
    public static AuthTypeEnum getEnum(int value) {
        for (AuthTypeEnum menuTypeEnum : AuthTypeEnum.values()) {
            if (menuTypeEnum.getValue() == value) {
                return menuTypeEnum;
            }
        }
        throw new UnknownEnumException("Error: Invalid AuthTypeEnum type value: " + value);
    }
}
