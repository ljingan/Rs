package com.common.net;

import com.common.net.codec.MessageDecoder;
import com.common.net.codec.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerBootStrap {

    private static final Logger logger = LoggerFactory.getLogger(ServerBootStrap.class);

    private int port;

    private ServerBootstrap bootstrap = null;

    /**
     * 用来接收进来的连接
     */
    private EventLoopGroup bossGroup;

    /**
     * 用来处理已经被接收的连接
     */
    private EventLoopGroup workerGroup;

    public ServerBootStrap() {

    }

    public void intBootstrap() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class).childHandler(
                new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws Exception {
                        // 注册handler
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new MessageDecoder());
                        pipeline.addLast("encoder", new MessageEncoder<ByteBuf>());
                        pipeline.addLast(new ServerHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
//                .option(ChannelOption.SO_SNDBUF, 16 * 1024) //设置发送数据缓冲大小
//                .option(ChannelOption.SO_RCVBUF, 16 * 1024) //设置接受数据缓冲大小
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }


    public void start() {
//        for (Integer port : ServerConfig.getSocketPort()) {
//            start(port);
//        }
    }

    public void start(int port) {
        try {
            intBootstrap();
            ChannelFuture f = bootstrap.bind(port).sync();
//            // 等待服务器 socket 关闭 。
            f.channel().closeFuture().sync();
            logger.info("服务器已启动,监听端口: " + port);
        } catch (Exception e) {
            logger.info("服务器启动失败: " + port);
        } finally {
            logger.info("ddddddddddddd,监听端口: " + port);
            stop();
        }
    }

    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

//    public static void main(String[] args) throws Exception {
//        logger.error("start server.....");
//        try {
//            final ServerBootStrap bootstrap = new ServerBootStrap();
//            bootstrap.start(8099);
//            logger.error("finish start server socket...");
//            logger.error("finish start server.....");
//            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//                public void run() {
//                    bootstrap.stop();
//                }
//            }));
//        } catch (Exception e) {
//            logger.error("server error", e);
//        }
//    }
}
