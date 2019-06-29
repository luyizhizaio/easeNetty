package com.kyrie.nio.demo.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kyrie on 2019/6/26.
 */
public class NioClient {

    public static void main(String[] args) {
        try{
            //客户端
            SocketChannel socketChannel=SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            //客户端向服务端发起连接
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            socketChannel.connect(new InetSocketAddress("localhost",8899));


            while(true){


                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();

                for(SelectionKey selectionKey : keySet){
                    if(selectionKey.isConnectable()){//客户端连接到服务端

                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        if(client.isConnectionPending()){
                            client.finishConnect(); //完成连接

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            writeBuffer.flip();

                            client.write(writeBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());

                            executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                while(true){
                                    try{
                                        writeBuffer.clear();
                                        InputStreamReader input =  new InputStreamReader(System.in);
                                        BufferedReader br = new BufferedReader(input);

                                        String sendMessage = br.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();

                                        client.write(writeBuffer);


                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }

                                }

                            }});

                        }
                        client.register(selector,SelectionKey.OP_READ);//读取事件




                    }else if(selectionKey.isReadable()){

                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                        int count = client.read(readBuffer);
                        if(count > 0 ){
                            String receivedMessage = new String(readBuffer.array(),0,count);
                            System.out.println(receivedMessage);
                        }
                    }
                }
                keySet.clear();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
