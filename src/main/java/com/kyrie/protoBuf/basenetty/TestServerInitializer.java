package com.kyrie.protoBuf.basenetty;

import com.kyrie.protoBuf.basedemo.DataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {

    ChannelPipeline pipeline = ch.pipeline();
    //4个关于protobuf的处理器
    pipeline.addLast(new ProtobufVarint32FrameDecoder());
    //指定传递的对象实例
    pipeline.addLast(new ProtobufDecoder(DataInfo.Student.getDefaultInstance()));
    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
    pipeline.addLast(new ProtobufEncoder());

    pipeline.addLast(new TestServerHandler());

  }
}
