package com.kyrie.socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client receive"+msg);

        ctx.writeAndFlush("from client"+ LocalDateTime.now());

    }

    /**
     * 通道连接后的回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //向服务端发送消息
        ctx.writeAndFlush("send to server!!!");


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
