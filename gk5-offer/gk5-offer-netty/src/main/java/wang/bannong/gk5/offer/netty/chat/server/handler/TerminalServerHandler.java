package wang.bannong.gk5.offer.netty.chat.server.handler;

import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import io.netty.channel.ChannelHandlerContext;
import wang.bannong.gk5.offer.netty.chat.processor.MsgProcessor;
import wang.bannong.gk5.offer.netty.chat.protocol.IMMessage;

/**
 * 用于处理Java控制台发过来的JavaObject消息体
 */
@Slf4j
public class TerminalServerHandler extends SimpleChannelInboundHandler<IMMessage> {

    private MsgProcessor processor = new MsgProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMMessage msg) throws Exception {
        processor.sendMsg(ctx.channel(), msg);
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("Socket Client: 与客户端断开连接:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }

}
