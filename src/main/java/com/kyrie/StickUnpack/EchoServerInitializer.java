package com.kyrie.StickUnpack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

        // 1024 最大字节长度，
        pipeline.addLast( new DelimiterBasedFrameDecoder(1024,delimiter));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new EchoServerHandler());

    }
}
