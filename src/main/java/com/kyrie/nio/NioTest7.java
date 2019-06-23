package com.kyrie.nio;

import java.nio.ByteBuffer;

/**
 * Created by Kyrie on 2019/6/23.
 * 只读buffer
 */
public class NioTest7 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for(int i =0; i< buffer.capacity(); ++i){
            buffer.put((byte)i);
        }

        //转成只读buffer
        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(readonlyBuffer.getClass());

    }

}
