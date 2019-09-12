package wang.bannong.gk5.util;

import org.junit.Test;

/**
 * Created by wang.bannong on 2017/8/4 15:19.
 */
public class MD5UtilTest {

    @Test
    public void test() {
        System.out.println(MD5Util.md5(""));
        System.out.println(MD5Util.md5(""));
        System.out.println(MD5Util.md5("lanjing"));
        System.out.println(MD5Util.md5("w***&"));
        System.out.println(MD5Util.md5("w***&"));
    }
}
