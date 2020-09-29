package wang.bannong.gk5.offer.netty.io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static wang.bannong.gk5.offer.netty.io.nio.chat.NIOChatServer.USER_CONTENT_SPILIT;
import static wang.bannong.gk5.offer.netty.io.nio.chat.NIOChatServer.USER_EXIST;
import static wang.bannong.gk5.offer.netty.io.nio.chat.NIOChatServer.charset;

public class NioServer {

    // 先来个大堂经理
    private Selector    selector;
    private Set<String> users = new HashSet<>();

    public static void main(String[] args) {
        new NioServer().listen();
    }

    public NioServer() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false); // 设置非堵塞
            serverSocketChannel.bind(new InetSocketAddress(NIOChatServer.port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务已启动，监听端口:" + NIOChatServer.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int s = selector.select();
                if (s == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeySet.iterator();
                while (it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    it.remove();
                    handler(selectionKey);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handler(SelectionKey selectionKey) {
        try {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel channel = serverSocketChannel.accept();
                channel.configureBlocking(false); // 设置
                channel.register(selector, SelectionKey.OP_READ);

                selectionKey.interestOps(SelectionKey.OP_ACCEPT);
                channel.write(NIOChatServer.charset.encode("请输入您昵称"));
            }

            // 处理来自客户端的数据读取请求
            if (selectionKey.isReadable()) {
                // 返回该SelectionKey对应的 Channel，其中有数据需要读取
                SocketChannel client = (SocketChannel) selectionKey.channel();
                ByteBuffer buff = ByteBuffer.allocate(1024);
                StringBuilder content = new StringBuilder();
                // 假如输入的数据大于1024字节长度呢？
                while (client.read(buff) > 0) {
                    buff.flip();
                    content.append(NIOChatServer.charset.decode(buff));
                }
                selectionKey.interestOps(SelectionKey.OP_READ);

                if (content.length() > 0) {
                    String[] arrayContent = content.toString().split(USER_CONTENT_SPILIT);
                    // 注册用户  这面相当于自己定于协议规则
                    if (arrayContent != null && arrayContent.length == 1) {
                        String nickName = arrayContent[0];
                        if (users.contains(nickName)) {
                            client.write(NIOChatServer.charset.encode(USER_EXIST));
                        } else {
                            users.add(nickName);
                            int onlineCount = onlineCount();
                            String message = "欢迎 " + nickName + " 进入聊天室! 当前在线人数:" + onlineCount;
                            broadcast(null, message);
                        }
                    } else if (arrayContent != null && arrayContent.length > 1) { // 注册完了，发送消息
                        String nickName = arrayContent[0];
                        String message = content.substring(nickName.length() + USER_CONTENT_SPILIT.length());
                        message = nickName + "说" + message;
                        if (users.contains(nickName)) {
                            //不回发给发送此内容的客户端
                            broadcast(client, message);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * selector中广播
     * TODO
     * @param client  发起广播的client【多余】
     * @param message 广播的信息
     */
    private void broadcast(SocketChannel client, String message) throws IOException {
        for (SelectionKey selectionKey : selector.keys()) {
            SelectableChannel channel = selectionKey.channel();
            if (channel instanceof SocketChannel) {
                ((SocketChannel) channel).write(charset.encode(message));
            }
        }
    }

    /**
     * 统计客户端在线人数
     */
    private int onlineCount() {
        int count = 0;
        for (SelectionKey key : selector.keys()) {
            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel) {
                ++count;
            }
        }
        return count;
    }
}
