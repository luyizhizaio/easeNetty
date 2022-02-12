package com.kyrie.heatbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Kyrie on 2019/5/19.
 */
public class HeatbeatClientHandler extends SimpleChannelInboundHandler<String> {
  /**
   * 接收服务端发过来的消息
   * @param ctx
   * @param msg
   * @throws Exception
   */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
