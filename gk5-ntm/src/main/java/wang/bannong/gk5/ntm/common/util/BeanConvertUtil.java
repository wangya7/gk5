package wang.bannong.gk5.ntm.common.util;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import wang.bannong.gk5.ntm.common.vo.DictVo;

/**
 * bean转换操作类
 * 
 * @author tuanzuo
 * @date 2019年4月11日
 */
public class BeanConvertUtil {
    /**
     * 转换枚举
     * 
     * @author tuanzuo
     * @date 2019年4月11日
     */
    public static List<DictVo> getEnumVos(Class<?> clzz, String... methods) throws Exception {
        List<DictVo> vos = new ArrayList<>();
        // 获取所有常量
        Object[] objects = clzz.getEnumConstants();
        String defaultMethod1 = "getCode";
        String defaultMethod2 = "getMsg";
        if (ArrayUtils.isNotEmpty(methods)) {
            if (methods.length == 1) {
                defaultMethod1 = methods[0];
            } else if (methods.length == 2) {
                defaultMethod1 = methods[0];
                defaultMethod2 = methods[1];
            }
        }
        // 获取指定方法
        Method getCode = clzz.getMethod(defaultMethod1);
        Method getMsg = clzz.getMethod(defaultMethod2);
        DictVo vo;
        for (Object obj : objects) {
            vo = new DictVo();
            vo.setCode(getCode.invoke(obj) == null ? null : getCode.invoke(obj).toString());
            vo.setName(getMsg.invoke(obj) == null ? null : getMsg.invoke(obj).toString());
            vos.add(vo);
        }
        return vos;
    }
}
