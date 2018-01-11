package com.common.net;

import com.common.handler.Dispatcher;
import com.common.handler.Handler;
import com.common.session.ServerContext;
import com.common.session.Session;
import com.common.session.impl.ServerContextImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ServerContext<Channel> serverContext = ServerContextImpl
            .getInstance();

    private Session<?> getSession(Channel connection) {
        return serverContext.getSession(connection);
    }

    public ServerHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Session session = getSession(ctx.channel());
        Request request = (Request) msg;
        request.setSession(session);
        doExcecute(request);
    }

    private void write(Response response) {
        Session<Channel> session = response.getSession();
        Channel channel = session.getConnection();
        channel.write(response);
    }

    /**
     * 消息处理
     *
     * @param request
     */
    private void doExcecute(Request request) {
        Response response = new Response(request);
        response.setBytes(request.getBytes());
        Handler handler = Dispatcher.get(request.getCmd());
        if (handler != null) {
            handler.exceute(request, response);
        }
        write(response);
    }

    /*
     *
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Session session = getSession(ctx.channel());
        logger.info("channelActive sessionId:{}, RamoteAddress:{}", session.getSessionId(), ctx.channel().remoteAddress());
        ctx.writeAndFlush("Welcome to "
                + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = getSession(ctx.channel());
        logger.info("channelInactive sessionId:{}", session.getSessionId());
        serverContext.removeSession(session);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 当出现异常就关闭连接
//        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Session session = getSession(ctx.channel());
        logger.info("channelReadComplete sessionId:{}, RamoteAddress:{}", session.getSessionId(), ctx.channel().remoteAddress());
        ctx.flush();
    }
}
