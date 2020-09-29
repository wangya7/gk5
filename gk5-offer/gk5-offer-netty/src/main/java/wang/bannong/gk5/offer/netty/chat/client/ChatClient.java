package wang.bannong.gk5.offer.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import wang.bannong.gk5.offer.netty.chat.client.handler.ChatClientHandler;
import wang.bannong.gk5.offer.netty.chat.protocol.IMDecoder;
import wang.bannong.gk5.offer.netty.chat.protocol.IMEncoder;

import java.io.IOException;

/**
 * 客户端
 * 完成Java控制台输入与服务端的交互逻辑
 *
 * @author Tom
 */
public class ChatClient {

    private ChatClientHandler clientHandler;
    private String            host;
    private int               port;

    public ChatClient(String nickName) {
        this.clientHandler = new ChatClientHandler(nickName);
    }

    public void connect(String host, int port) {
        this.host = host;
        this.port = port;

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                     .channel(NioSocketChannel.class)
                     .option(ChannelOption.SO_KEEPALIVE, true)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         public void initChannel(SocketChannel ch) throws Exception {
                             ch.pipeline().addLast(new IMDecoder());
                             ch.pipeline().addLast(new IMEncoder());
                             ch.pipeline().addLast(clientHandler);
                         }
                     });

            ChannelFuture f = bootstrap.connect(this.host, this.port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws IOException {
        new ChatClient("Cover").connect("127.0.0.1", 8080);
        String url = "http://localhost:8080/images/a.png";
        System.out.println(url.toLowerCase().matches(".*\\.(gif|png|jpg)$"));
    }

}