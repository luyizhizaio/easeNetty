package com.kyrie.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketClient {

    public static void main(String[] args) throws InterruptedException {
      //客户端只需要一个事件循环组
      EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

      try {
        //客户端启动类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MySocketClientInitlizer());
        ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();

        channelFuture.channel().closeFuture().sync();
      }finally{
        eventLoopGroup.shutdownGracefully();
      }
    }
}
