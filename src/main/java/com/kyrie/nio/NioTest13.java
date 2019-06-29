package com.kyrie.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 字符集编解码问题
 * Created by Kyrie on 2019/6/27.
 */
public class NioTest13 {

    public static void main(String[] args) throws Exception {

        String input ="NioTest13_in.txt";
        String output ="NioTest13_out.txt";


        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(input,"r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(output,"rw");

        long inputLength = new File(input).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        //内存映射文件
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY,0 ,inputLength);

        //字符集
//        Charset charset = Charset.forName("utf-8");
        Charset charset = Charset.forName("iso-8859-1");
        //解码器，字节数组转成字符串
        CharsetDecoder decoder = charset.newDecoder();
        //编码器 字符串转成字节数组
        CharsetEncoder encoder = charset.newEncoder();


        CharBuffer charBuffer = decoder.decode(inputData);

        ByteBuffer outputData = encoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

        //可用字符集
        Charset.availableCharsets().forEach((k,v) ->{
                System.out.println(k +","+ v);

        });

    }
}
