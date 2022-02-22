package com.kyrie.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NIOClient {
  public static void main(String[] args) throws Exception {

    SocketChannel socketChannel = SocketChannel.open();

    socketChannel.connect(new InetSocketAddress("localhost", 7001));


    String fileName = "";

    FileChannel channel = new FileInputStream(fileName).getChannel();

    long transferCount = channel.transferTo(0, channel.size(), socketChannel);

    System.out.println("发送总的字节数=" + transferCount);

  }
}
