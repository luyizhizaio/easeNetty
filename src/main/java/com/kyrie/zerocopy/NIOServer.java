package com.kyrie.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {
  public static void main(String[] args) throws Exception{

    InetSocketAddress address = new InetSocketAddress(7001);
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

    ServerSocket serverSocket = serverSocketChannel.socket();

    serverSocket.bind(address);

    ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

    while (true){
      SocketChannel socketChannel = serverSocketChannel.accept();
      int readcount =0;
      while(-1 != readcount){
        try {
          readcount = socketChannel.read(byteBuffer);
          System.out.println("readcount = "+ readcount);
        }catch(Exception e){
          break;
        }
        byteBuffer.rewind(); //倒带，设置position=0，mark作废
      }
    }

  }
}
