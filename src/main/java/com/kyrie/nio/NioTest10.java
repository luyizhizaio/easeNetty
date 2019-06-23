package com.kyrie.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest10 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        //获取锁
        FileLock fileLock = fileChannel.lock(3,6,true);


        System.out.println("valid:"+ fileLock.isValid());
        System.out.println("lock type:" + fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();
    }
}
