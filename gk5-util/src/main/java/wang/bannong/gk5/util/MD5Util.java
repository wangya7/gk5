package wang.bannong.gk5.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;


/**
 * Created by wang.bannong on 2017/8/4 13:47.
 */
public final class MD5Util {

    private final static Logger logger = LogManager.getLogger(MD5Util.class);

    public static String md5(final String src) {
        try {
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");
            byte[] inputByteArray = src.getBytes();

            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);

            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();

            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            logger.error("MD5 coding error[{}]", src);
            throw new RuntimeException();
        }
    }

    public static String md5LowerCase(final String src) {
        return md5(src).toLowerCase();
    }

    /**
     * 128bits -> 16 characters
     * @param byteArray
     * @return
     */
    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F'};
        char[] resultCharArray =new char[byteArray.length * 2];

        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    public static void main(String... args) {
        System.out.println("11       : " + md5LowerCase("11"));
        System.out.println("123456   : " + md5LowerCase("lj532630"));
        System.out.println("111111   : " + md5LowerCase("111111"));
        System.out.println("666666   : " + md5LowerCase("666666"));
    }

}
