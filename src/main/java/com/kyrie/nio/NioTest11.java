package com.kyrie.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering与Gathering
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest11 {

    public static void main(String[] args) throws Exception {

        //socketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(address);

        int messageLength = 2+3+4;

        //定义ByteBuffer数组，每个byteBuffer分别读取不同长度的数据。
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while(true){

            int bytesRead = 0;

            while(bytesRead < messageLength){
                //读取socketchannel中的数据 ,为scattering
                long r = socketChannel.read(buffers);

                bytesRead += r;
                System.out.println("bytesRead: "+bytesRead);

                //打印buffer数组的position和limit
                Arrays.asList(buffers).stream()
                        .map(buffer -> "position: "+ buffer.position() + ", limit: "+ buffer.limit())
                        .forEach(System.out::println);
            }


            Arrays.asList(buffers).forEach(buffer -> {
                buffer.flip();
            });

            //写操作
            long bytesWriten = 0;


            while(bytesWriten < messageLength){
                //写回channel，为gathering
                long r = socketChannel.write(buffers);
                bytesWriten += r;

            }

            Arrays.asList(buffers).forEach(buffer ->{
                buffer.clear();
            });


            System.out.println("bytesRead:"+bytesRead + ",bytesWriten:"+bytesWriten + ",messageLength:"+messageLength);
            System.out.println();
            System.out.println();




        }


    }
}
