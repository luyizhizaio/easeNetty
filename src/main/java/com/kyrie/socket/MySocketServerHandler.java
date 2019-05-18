package com.kyrie.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * ���ͣ���ʾ����˺Ϳͻ��˴������������
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     *
     * @param ctx��
     * @param msg ���յ�����Ϣ
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


        System.out.println(ctx.channel().remoteAddress()+ ","+ msg);


        //��Ӧ���ͻ��˵�����
        ctx.channel().writeAndFlush("from server:"+ UUID.randomUUID());

    }

    /**
     * �쳣������
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close(); //�����쳣���ر����ӡ�

    }
}
