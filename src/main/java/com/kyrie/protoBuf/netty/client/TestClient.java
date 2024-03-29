package com.kyrie.protoBuf.netty.client;

import com.kyrie.broadcast.client.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class TestClient {


    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899);
            channelFuture.channel().closeFuture().sync();

        }finally{
            eventLoopGroup.shutdownGracefully();
        }
    }
}
