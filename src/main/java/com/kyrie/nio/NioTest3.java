package com.kyrie.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Kyrie on 2019/5/25.
 * 输出到文件
 */
public class NioTest3 {

    public static void main(String[] args) throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream("NoiTest3.txt");

        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] messages = "hello hefei, nihao".getBytes();

        //输入
        for (int i = 0; i <messages.length ; i++) {
            byteBuffer.put(messages[i]);
        }

        //反转
        byteBuffer.flip();

        //输出到文件
        fileChannel.write(byteBuffer);

        fileOutputStream.close();

    }
}
