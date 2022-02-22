package com.kyrie.heatbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by Kyrie on 2019/5/19.
 */
public class MyHeatbeatServerInitializer  extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //空闲状态检测的处理器 指定（读空闲时间，写空闲时间，读写空闲时间）
        pipeline.addLast(new IdleStateHandler(8,6,20));
        pipeline.addLast(new MyHeatbeatServerHandler());
    }
}
