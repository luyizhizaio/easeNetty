package com.kyrie.broadcast.client;

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
 * Created by Kyrie on 2019/5/19.
 */
public class MyChatClient {


    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).channel();

            //channelFuture.channel().closeFuture().sync();

            //从控制台读取输入数据
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //死循环,向服务端发送消息，
            for(;;){
                channel.writeAndFlush(br.readLine() + "\r\n");
            }


        }finally{
            eventLoopGroup.shutdownGracefully();

        }


    }
}
