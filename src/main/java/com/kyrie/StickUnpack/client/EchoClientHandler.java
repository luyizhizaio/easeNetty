package com.kyrie.StickUnpack.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {

    private int counter = 0;

    private String echo_msg = " one echo message";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println( " this is " + ++counter +"times receive server [" +msg +"]");

    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
      System.out.println("channelActive。。。。");
        String msg = echo_msg +"$_";
        ctx.writeAndFlush(msg);
    }
}
