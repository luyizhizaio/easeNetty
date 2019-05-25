package com.kyrie.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Kyrie on 2019/5/25.
 * 传统io切换至OIN
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {

        //获取输入流
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        //获取channel
        FileChannel fileChannel = fileInputStream.getChannel();

        //定义buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        //输入到byteBuffer
        fileChannel.read(byteBuffer);

        //反转
        byteBuffer.flip();

        //输出
        while(byteBuffer.remaining() >0){

            byte b = byteBuffer.get();

            System.out.println("Character:" +(char)b);

        }

        fileInputStream.close();

    }

}
