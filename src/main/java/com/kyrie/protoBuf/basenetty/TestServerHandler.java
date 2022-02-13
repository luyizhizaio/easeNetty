package com.kyrie.protoBuf.basenetty;

import com.kyrie.protoBuf.basedemo.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 范型：指定传递的protobuf的类
 */
public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student stud) throws Exception {

    System.out.println(stud.getName());
    System.out.println(stud.getAge());
    System.out.println(stud.getAddress());

  }
}
