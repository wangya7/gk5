package wang.bannong.gk5.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by bn. on 2018/9/30 上午11:44
 */
public class Base64Utils {

    public static byte[] encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    public static byte[] decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * 先将内容编码成Base64结果;
     * 将结果中的加号”+”替换成中划线“-“;
     * 将结果中的斜杠”/”替换成下划线”_”;
     * 将结果中尾部的“=”号全部保留;
     *
     * @param str 编码字符串
     * @return 编码后的字符串
     */
    public static String safeBase64(String str) {
        String encodeStr = "";
        try {
            encodeStr = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
        String safeEncodeStr = encodeStr.replaceAll("\\+", "-");
        return safeEncodeStr.replaceAll("\\/", "_");
    }
}
