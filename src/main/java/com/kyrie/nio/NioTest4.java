package com.kyrie.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Kyrie on 2019/6/3.
 * 读取文件数据写道另一个文件
 */
public class NioTest4 {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();

        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while(true){
            //buffer.clear();
            //写入buffer；Reads a sequence of bytes from this channel into the given buffer.
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
