package wang.bannong.gk5.offer.netty.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO的操作过于繁琐，于是才有了Netty
 * Netty就是对这一系列非常繁琐的操作进行了封装
 * <p>
 * Created by Tom.
 */
public class NIOServerDemo {

    public static void main(String[] args) throws Exception {
        // 1. 构造大唐经理
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));

        // 下面两句顺序不能倒 先设置非堵塞，然后才能注册selector大堂经理
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //缓冲区 Buffer 等候区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 2. 循环值
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterable = selectionKeys.iterator();

            while (iterable.hasNext()) {
                SelectionKey key = iterable.next();
                iterable.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 这个方法体现非阻塞，不管你数据有没有准备好
                    // 你给我一个状态和反馈
                    SocketChannel channel = server.accept();
                    // 一定一定要记得设置为非阻塞
                    channel.configureBlocking(false);

                    // 当数据准备就绪的时候，将状态改为可读
                    key = channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // key.channel 从多路复用器中拿到客户端的引用
                    SocketChannel channel = (SocketChannel) key.channel();
                    int len = channel.read(buffer);
                    if (len > 0) {
                        buffer.flip();
                        String content = new String(buffer.array(), 0, len);
                        key = channel.register(selector, SelectionKey.OP_WRITE);
                        // 在key上携带一个附件，一会再写出去
                        key.attach(content);
                        System.out.println("读取内容：" + content);
                    }
                } else if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    String content = (String) key.attachment();
                    channel.write(ByteBuffer.wrap(("输出：" + content).getBytes()));
                    channel.close();
                }
            }
        }
    }


}
