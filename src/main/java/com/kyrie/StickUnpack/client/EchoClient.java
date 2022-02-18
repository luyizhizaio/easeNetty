package com.kyrie.StickUnpack.client;

import com.kyrie.socket.client.MySocketClientInitlizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoClient {
    public static void main(String[] args) throws  Exception {

        //客户端只需要一个事件循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //客户端启动类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>(){

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            // 1024 最大字节长度，
                            pipeline.addLast( new DelimiterBasedFrameDecoder(1024,delimiter));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();

            channelFuture.channel().closeFuture().sync();
        }finally{
            eventLoopGroup.shutdownGracefully();
        }
    }
}
