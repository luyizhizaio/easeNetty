package com.kyrie.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 判断空闲状态，打印日志
 * Created by Kyrie on 2019/5/19.
 */
public class MyHeatbeatServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;

            switch(event.state()){
                case READER_IDLE:
                    eventType="读空闲";
                case WRITER_IDLE:
                    eventType="写空闲";
                case ALL_IDLE:
                    eventType="读写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + "超时事件：" + eventType);
            ctx.channel().close();


        }
    }
}
