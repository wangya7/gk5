package wang.bannong.gk5.offer.netty.rpc.protocol;

import lombok.Data;
import java.io.Serializable;

/**
 * 自定义传输协议
 */
@Data
public class InvokerProtocol implements Serializable {
    private static final long serialVersionUID = -8032986870751796110L;

    private String className;//类名
    private String methodName;//函数名称 
    private Class<?>[] parames;//形参列表
    private Object[] values;//实参列表

}
