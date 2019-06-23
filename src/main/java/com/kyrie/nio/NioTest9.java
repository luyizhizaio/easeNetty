package com.kyrie.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 映射内存文件
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest9 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();
        //获取mappedByteBuffer ，map方法参数（模式，映射的开始位置，映射大小）
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);

        //修改指定位置的数据
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');

        randomAccessFile.close();

    }
}
