package com.kyrie.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓存区
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest8 {

    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();

        FileChannel outputChannel = outputStream.getChannel();

        //分配直接缓存区
        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        while(true){
            buffer.clear();
            //写入buffer
            int read = inputChannel.read(buffer);
            System.out.println("read:"+read);

            if(-1 == read){ //读取结束
                break;
            }

            buffer.flip();
            //写向channel
            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }




}
