package com.kyrie.nio.demo.communication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Kyrie on 2019/6/24.
 */
public class NioServer {

    private static Map<String,SocketChannel> clientMap = new HashMap();

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //配置非阻塞
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //将channel注册到selector对象上。可以注册多个channel
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //处理事件
        while(true){
            try{
                selector.select(); //该方法阻塞
                //获取到事件的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                //遍历selectionKeys获取selectionKey
                selectionKeys.forEach(selectionKey -> {

                    final SocketChannel client;

                    try{
                        //客户端向服务端发起连接
                        if(selectionKey.isAcceptable()){
                            //事件channel
                            ServerSocketChannel server= (ServerSocketChannel) selectionKey.channel();
                            //获取客户端连接
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            //记录客户端的连接
                            String key = "["+ UUID.randomUUID().toString() +"]";
                            clientMap.put(key,client);
                        }else if(selectionKey.isReadable()){//读取客户端传过来的消息
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                            int count = client.read(readBuffer);

                            if(count > 0 ){
                                readBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());

                                System.out.println(client +":" + receivedMessage);

                            }

                        }

                    }catch(Exception e){
                        e.printStackTrace();

                    }

                });

                selectionKeys.clear(); //清空集合




            }catch(Exception e){
                e.printStackTrace();
            }



        }


    }
}
