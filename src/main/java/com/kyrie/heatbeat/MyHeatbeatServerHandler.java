package com.kyrie.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * ≈–∂œø’œ–◊¥Ã¨£¨¥Ú”°»’÷æ
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
                    eventType="∂¡ø’œ–";
                case WRITER_IDLE:
                    eventType="–¥ø’œ–";
                case ALL_IDLE:
                    eventType="∂¡–¥ø’œ–";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + "≥¨ ± ¬º˛£∫" + eventType);
            ctx.channel().close();


        }
    }
}
