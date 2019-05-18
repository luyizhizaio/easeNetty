package com.kyrie.first;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * Created by Kyrie on 2019/5/18.
 */
public class TestServerInitalizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline =ch.pipeline();

        //���ڶ��������Ӧ���б����
        pipeline.addLast("httpServerCodec",new HttpServerCodec());

        //ҵ����
        pipeline.addLast("tesHttpServerHandler",new TesHttpServerHandler());


    }
}
