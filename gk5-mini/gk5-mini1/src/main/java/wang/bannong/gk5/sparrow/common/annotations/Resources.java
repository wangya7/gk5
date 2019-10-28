package wang.bannong.gk5.sparrow.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import wang.bannong.gk5.sparrow.enums.AuthTypeEnum;

/**
 * 权限认证注解
 *
 * @author Caratacus
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resources {

    /**
     * 权限认证类型
     *
     * @see AuthTypeEnum
     */
    AuthTypeEnum auth() default AuthTypeEnum.OPEN;
}
