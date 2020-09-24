package com.xue.study.netty;

import com.xue.study.config.SocketConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author: mf015
 * Date: 2020/7/16 0016
 */
@Component
public class NettyServer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    public void serverStart() {
//        System.out.println("port***" + SocketConfig.SOCKET_PORT);//TODO 为什么是0 ？？？？？？？？？？？？？

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            LOGGER.info("----------有客户端接入， socket={}---------------------",
                                    socketChannel.localAddress().getAddress() + ":" + socketChannel.localAddress().getPort());

                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                            pipeline.addLast(new ServerHandler());
//                            pipeline.addLast(new StringEncoder(), new StringDecoder());
//                            pipeline.addLast("byteArrayDecoder", new ByteArrayDecoder());
//                            pipeline.addLast("byteArrayEncoder", new ByteArrayEncoder());
                        }
                    });
            System.out.println("+++++++++++++++++++"+SocketConfig.SOCKET_PORT);
            LOGGER.info("nettyServer 开始启动--------------");
            ChannelFuture channelFuture = serverBootstrap.bind(SocketConfig.SOCKET_PORT).sync();
            LOGGER.info("nettyServer 启动成功------------");
            channelFuture.channel().closeFuture().sync();
            LOGGER.info("nettyServer 关闭服务器通道---------");
        } catch (InterruptedException e) {
            //e.printStackTrace();
            LOGGER.error("serverStart encountered exception: ", e);
        } finally {
            LOGGER.error("nettyServer 优雅关闭----------");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void run() {
        this.serverStart();
    }
}
