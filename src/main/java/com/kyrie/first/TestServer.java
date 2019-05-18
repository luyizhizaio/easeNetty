package com.kyrie.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现http服务
 * Created by Kyrie on 2019/5/18.
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        //事件循环组，boss接受连接交给worker，worker具体处理连接。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup  = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitalizer()); //

            //绑定端口
            ChannelFuture future = serverBootstrap.bind(8899).sync();

            future.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }


    }
}
