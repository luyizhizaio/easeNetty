package com.kyrie.heatbeat;

import com.kyrie.broadcast.MyChatInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by Kyrie on 2019/5/19.
 */
public class MyHeatbeatServer {

    public static void main(String[] args) throws InterruptedException {
        //�¼�ѭ���飬boss�������ӽ���worker��worker���崦�����ӡ�
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) //����bossgroup�Ĵ�����
                    .childHandler(new MyHeatbeatServerInitializer()); //����workerGroup�Ĵ�����


            //sync ��ʾ�ȴ�
            ChannelFuture channelFuture = serverBootstrap.bind("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }


    }

}
