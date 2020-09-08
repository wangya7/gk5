package wang.bannong.gk5.offer.jdk.net.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.StringJoiner;

/**
 * NIO三大套间：Buffer，Selector，Channel
 */
public class NioBase {

    public static final String TEST_TEXT = "/Users/wangya/backend/code-repository/github/gk5/gk5-offer/gk5-offer-jdk/src/main/resources/test.txt";
    public static final String TEST_TMP = "/Users/wangya/backend/code-repository/github/gk5/gk5-offer/gk5-offer-jdk/src/main/resources/tmp.txt";

    /**
     * 本质上是一个数组
     */
    @Test
    public void bufferTest() throws Exception {

        // 基础的操作
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(2 * (i + 1));
        }

        buffer.flip();

        // 注意下面这三个参数
        println("分配完", buffer);

        StringJoiner sj = new StringJoiner(",");
        while (buffer.hasRemaining()) {
            sj.add(String.valueOf(buffer.get()));
        }
        System.out.println(sj.toString());
        println("打印完", buffer);

        // 直接缓冲区
        FileInputStream fis = new FileInputStream(TEST_TEXT);
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(TEST_TMP);
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocateDirect(1024);

        for(;;) {
            buffer1.clear();
            int length = fisChannel.read(buffer1);

            if (length == -1) {
                break;
            }

            buffer1.flip();
            fosChannel.write(buffer1);
        }
    }

    @Test
    public void selectorTest() {

    }

    @Test
    public void channelTest() {

    }


    public static void println(String step, Buffer buffer) {
        System.out.println("Step:" + step);
        System.out.println("\tposition =" + buffer.position());
        System.out.println("\tlimit    =" + buffer.limit());
        System.out.println("\tcapacity =" + buffer.capacity());
    }

}
