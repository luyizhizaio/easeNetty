package com.kyrie.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest12 {

    public static void main(String[] args) throws IOException {

        //监控5个端口

        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        //构造selector

        Selector selector = Selector.open();

        for (int i=0 ; i<ports.length; ++i){
            //网络channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //调整阻塞模式,false 为非阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            //绑定多个端口
            serverSocket.bind(address);


            //注册 接受连接操作。
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口"+ports[i]);

        }

        while(true){
            int numbers = selector.select();
            System.out.println("numbers: "+numbers);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectedKeys:" + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if(selectionKey.isAcceptable()){ //接受连接发生

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    //注册read 操作
                    socketChannel.register(selector,SelectionKey.OP_READ);

                    //移除当前处理过的事件
                    iterator.remove();
                    System.out.println("获取客户端连接：" + socketChannel);
                } else if(selectionKey.isReadable()){ //读取操作发生
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;
                    while(true){
                        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);
                        if(read <= 0 ){
                            break;
                        }
                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);
                        bytesRead += read;

                    }
                    System.out.println("读取： "+ bytesRead + ",来自于：" + socketChannel);
                    iterator.remove();

                }
            }
        }
    }
}
