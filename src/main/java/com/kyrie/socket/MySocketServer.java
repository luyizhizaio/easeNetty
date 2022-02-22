package com.kyrie.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * socket服务端
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketServer {

    public static void main(String[] args) throws InterruptedException {
        //事件循环组，boss接受连接交给worker，worker具体处理连接。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup  = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
              .channel(NioServerSocketChannel.class)//使用 NioServerSocketChannel作为通道实现
              .option(ChannelOption.SO_BACKLOG,128)//设置线程队列得到的连接个数
              .childOption(ChannelOption.SO_KEEPALIVE,true)//设置保持活动连接状态
              .childHandler(new MySocketServerInItializer());

            //绑定端口，并且同步
            ChannelFuture  channelFuture = serverBootstrap.bind(8899).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
