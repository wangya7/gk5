package wang.bannong.gk5.offer.lld.chain_of_responsibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

/**
 * 模拟客户端调用
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/4
 */
public class PipelineClient {

    /**
     * 模式介绍来自： https://mp.weixin.qq.com/s/bBZFDx6WwRfTnfQS08Mesg
     * 通过把流程行程一个个的Stage然后再根据业务动态的组合行成灵活的业务流程。
     * 每个业务就是一个Stage列表，依次按照顺序执行，所以可以按照文中说的借助Spring提前加载。
     *
     * Pipeline 模式 与其说责任链的变种，其实完全没有多少关系，责任链我们是通过一个successor定义下一个参与者执行，
     * 行程一个“链”，
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(testFunctionInterface("hello"));
    }


    /**
     * 理解只有一个方法的接口其实就是 函数接口，默认可以转换成 FunctionInterface
     * @param <T>
     */
    @FunctionalInterface
    public interface Hi<T> {
        T execute(T factor);
    }

    public static class HiX implements Hi<String> {
        @Override
        public String execute(String factor) {
            return "HiX:" + factor;
        }
    }

    public static String testFunctionInterface(String context) {
        HiX hi1 = new HiX();
        HiX hi2 = new HiX();
        HiX hi3 = new HiX();
        List<Hi<String>> his = new ArrayList<>(Arrays.asList(hi1, hi2, hi3));
        PipelineClient client = new PipelineClient();
        List<Hi<String>> enhancedFunctionList = his
            .stream()
            .map(client::hiFunc) // 注意这一步是没有任何操作的，只是封装成一个Function，当执行完这个以后 enhancedFunctionList 中的成员就不是原来的 hi1，hi2，hi3 啦，而是重新经过包装的 Function
            // .map(client::hiFunc2) // 注意这一步是没有任何操作的，只是封装成一个Function，当执行完这个以后 enhancedFunctionList 中的成员就不是原来的 hi1，hi2，hi3 啦，而是重新经过包装的 Function
            .collect(Collectors.toList());


        // 获取执行结果
        return client.getPipeLineResult(context, enhancedFunctionList).orElse(context);
    }

    public <T> Hi<T> hiFunc(Hi<T> hi) {
        return factor -> {
            System.out.println(System.currentTimeMillis());
            T r = hi.execute(factor);
            System.out.println("模拟执行了其他的逻辑");
            System.out.println(System.currentTimeMillis());
            return r;
        };
    }

    // 使用这个就不行
    public <T> Function<Hi, T> hiFunc2(Hi<T> hi) {
        return factor -> {
            System.out.println(System.currentTimeMillis());
            T r = hi.execute((T) factor);
            System.out.println("模拟执行了其他的逻辑");
            System.out.println(System.currentTimeMillis());
            return r;
        };
    }

    private <T> Optional<T> getPipeLineResult(T context, List<Hi<T>> functionList) {
        if (CollectionUtils.isEmpty(functionList)) {
            return Optional.empty();
        }

        // 执行每一个stage
        for (Hi<T> f : functionList) {
            if(Objects.isNull(context)) {
                return Optional.empty();
            }
            context = f.execute(context);
        }

        return Optional.ofNullable(context);
    }

}
