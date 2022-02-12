package com.kyrie.socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.time.LocalDateTime;

/**
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketClientHandler extends SimpleChannelInboundHandler<String> {
  /**
   * 服务器端向客户端发送消息处理方法
   * @param ctx
   * @param msg
   * @throws Exception
   */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
      System.out.println(ctx.channel().remoteAddress());
      System.out.println("client receive"+msg);

      //客户端向服务端发送消息
      ctx.writeAndFlush("from client"+ LocalDateTime.now());
    }

    /**
     * 通道连接后的回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //链接建立之后，客户端第一次向服务端发送消息，触发
        ctx.writeAndFlush("send to server!!!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
