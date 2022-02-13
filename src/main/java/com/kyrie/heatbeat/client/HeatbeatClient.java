package com.kyrie.heatbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeatbeatClient {

  public static void main(String[] args) throws InterruptedException, IOException {
    EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
        .handler(new HeatbeatClientInitializer());

      Channel channel = bootstrap.connect("localhost", 8899).channel();

      //从控制台读取输入数据
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      //死循环,向服务端发送消息，
      for(;;){
        String msg = br.readLine();
        if(msg.equals("bye")){
          break;
        }else{
          channel.writeAndFlush(msg + "\r\n");
        }
      }
    }finally{
      eventLoopGroup.shutdownGracefully();
    }
  }

}
