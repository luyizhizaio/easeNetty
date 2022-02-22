package com.kyrie.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by Kyrie on 2019/5/18.
 */
public class MySocketServerInItializer extends ChannelInitializer<SocketChannel> {
  /**
   *
   * @param ch
   * @throws Exception
   */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline  pipeline= ch.pipeline();
        pipeline.addLast("LengthFieldBasedFrameDecoder",
          new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast(new MySocketServerHandler());//自定义处理器，不能用单例对象
    }
}
