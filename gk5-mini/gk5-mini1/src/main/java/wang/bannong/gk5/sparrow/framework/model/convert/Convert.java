package wang.bannong.gk5.sparrow.framework.model.convert;

import java.io.Serializable;

import wang.bannong.gk5.sparrow.framework.converter.BeanConverter;

/**
 * 普通实体父类
 */
public class Convert implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 获取自动转换后的JavaBean对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T convert(Class<T> clazz) {
        return BeanConverter.convert(clazz, this);
    }
}
