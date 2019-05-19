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

        //����״̬���Ĵ�������������ʱ�䣬д����ʱ�䣬��д����ʱ�䣩
        pipeline.addLast(new IdleStateHandler(5,10,20));
        pipeline.addLast(new MyHeatbeatServerHandler());
    }
}
