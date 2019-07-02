package com.kyrie.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Random;

/**
 * 负责连接服务器，声明通道，连接通道
 * Created by Kyrie on 2019/7/2.
 */
public class MyClient implements Runnable {

    private static Integer PORT =8899;
    private static String ip = "localhost";

    private AsynchronousSocketChannel asynSocketChannel;


    public MyClient() throws IOException {
        asynSocketChannel = AsynchronousSocketChannel.open(); //打开通道

    }
    public void connect(){
        asynSocketChannel.connect(new InetSocketAddress(ip, PORT));  // 创建连接 和NIO一样
    }

    public void write(String request){
        try {
            asynSocketChannel.write(ByteBuffer.wrap(request.getBytes())).get();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            asynSocketChannel.read(byteBuffer).get();
            byteBuffer.flip();
            byte[] respByte = new byte[byteBuffer.remaining()];
            byteBuffer.get(respByte); // 将缓冲区的数据放入到 byte数组中
            System.out.println(new String(respByte,"utf-8").trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            MyClient myClient = new MyClient();
            myClient.connect();
            new Thread(myClient, "myClient").start();
            String []operators = {"+","-","*","/"};
            Random random = new Random(System.currentTimeMillis());
            String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
            myClient.write(expression);
        }
    }
}
