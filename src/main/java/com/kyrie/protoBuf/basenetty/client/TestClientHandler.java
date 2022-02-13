package com.kyrie.protoBuf.basenetty.client;

import com.kyrie.protoBuf.basedemo.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 范型：传递的对象
 */
public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
  /**
   * 接收服务端发过来的消息
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("send message");
    //链接建立向服务端发送一条消息
    DataInfo.Student stud = DataInfo.Student.newBuilder().setName("小越").setAge(23).setAddress("北京").build();
    ctx.writeAndFlush(stud);
  }

}
