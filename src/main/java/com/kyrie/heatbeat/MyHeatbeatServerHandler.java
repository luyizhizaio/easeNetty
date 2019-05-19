package com.kyrie.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * �жϿ���״̬����ӡ��־
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
                    eventType="������";
                case WRITER_IDLE:
                    eventType="д����";
                case ALL_IDLE:
                    eventType="��д����";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + "��ʱ�¼���" + eventType);
            ctx.channel().close();


        }
    }
}
