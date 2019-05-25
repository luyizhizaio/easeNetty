package com.kyrie.protoBuf.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

        //接受客户端的Person

        System.out.println(msg.toString());



    }



}
