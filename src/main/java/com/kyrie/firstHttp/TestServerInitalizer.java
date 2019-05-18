package com.kyrie.firstHttp;

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

        //用于对请求和响应进行编解码组件
        pipeline.addLast("httpServerCodec",new HttpServerCodec());

        //业务处理
        pipeline.addLast("tesHttpServerHandler",new TesHttpServerHandler());


    }
}
