package com.kyrie.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * 泛型：表示服务端和客户端传输的数据类型
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     *
     * @param ctx：
     * @param msg ：收到的消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


        System.out.println(ctx.channel().remoteAddress()+ ","+ msg);


        //响应给客户端的数据
        ctx.channel().writeAndFlush("from server:"+ UUID.randomUUID());

    }

    /**
     * 异常处理方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close(); //出现异常，关闭连接。

    }
}
