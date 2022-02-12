package com.kyrie.protoBuf.netty;

import com.kyrie.heatbeat.MyHeatbeatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by Kyrie on 2019/5/25.
 * netty 传递protobuff
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //事件循环组，boss接受连接交给worker，worker具体处理连接。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) //用于bossgroup的处理器
                    .childHandler(new TestServerInitializer()); //用于workerGroup的处理器


            //sync 表示等待
            ChannelFuture channelFuture = serverBootstrap.bind("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }


    }

}
