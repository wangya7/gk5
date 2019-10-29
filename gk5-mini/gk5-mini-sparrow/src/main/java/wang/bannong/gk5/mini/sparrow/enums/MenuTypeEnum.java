package wang.bannong.gk5.mini.sparrow.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import wang.bannong.gk5.mini.sparrow.common.exception.UnknownEnumException;
import wang.bannong.gk5.mini.sparrow.framework.enums.IEnum;

/**
 * 菜单类型枚举
 */
public enum MenuTypeEnum implements IEnum {

    /**
     * 目录
     */
    CATALOG(1),
    /**
     * 菜单
     */
    MENU(2),
    /**
     * 按钮
     */
    BUTTON(3);

    @EnumValue
    private final int value;

    MenuTypeEnum(final int value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public int getValue() {
        return this.value;
    }


    @JsonCreator
    public static MenuTypeEnum getEnum(int value) {
        for (MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()) {
            if (menuTypeEnum.getValue() == value) {
                return menuTypeEnum;
            }
        }
        throw new UnknownEnumException("Error: Invalid MenuTypeEnum type value: " + value);
    }
}
