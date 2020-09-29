package wang.bannong.gk5.offer.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.offer.netty.chat.protocol.IMDecoder;
import wang.bannong.gk5.offer.netty.chat.protocol.IMEncoder;
import wang.bannong.gk5.offer.netty.chat.server.handler.HttpServerHandler;
import wang.bannong.gk5.offer.netty.chat.server.handler.TerminalServerHandler;
import wang.bannong.gk5.offer.netty.chat.server.handler.WebSocketServerHandler;

/**
 * 服务端的基本思路：所有客户端消息全部发送到服务端的消息容器，每一个消息都携带客户端的标识信息，
 * 然后服务端发给所有在线的客户端。
 */
@Slf4j
public class ChatServer {

    private int port = 8080;

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .option(ChannelOption.SO_BACKLOG, 1024)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         public void initChannel(SocketChannel ch) throws Exception {

                             ChannelPipeline pipeline = ch.pipeline();

                             /** 解析自定义协议 */
                             pipeline.addLast(new IMDecoder());  //Inbound
                             pipeline.addLast(new IMEncoder());  //Outbound
                             pipeline.addLast(new TerminalServerHandler());  //Inbound

                             /** 解析Http请求 */
                             pipeline.addLast(new HttpServerCodec());  //Outbound
                             //主要是将同一个http请求或响应的多个消息对象变成一个 fullHttpRequest完整的消息对象
                             pipeline.addLast(new HttpObjectAggregator(64 * 1024));//Inbound
                             //主要用于处理大数据流,比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的 ,加上这个handler我们就不用考虑这个问题了
                             pipeline.addLast(new ChunkedWriteHandler());//Inbound、Outbound
                             pipeline.addLast(new HttpServerHandler());//Inbound

                             /** 解析WebSocket请求 */
                             pipeline.addLast(new WebSocketServerProtocolHandler("/im"));    //Inbound
                             pipeline.addLast(new WebSocketServerHandler()); //Inbound

                         }
                     });
            ChannelFuture f = bootstrap.bind(this.port).sync();

            log.info("服务已启动,监听端口" + this.port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws IOException {
        new ChatServer().start();
    }

}
