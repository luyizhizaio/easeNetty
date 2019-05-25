package com.kyrie.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Created by Kyrie on 2019/5/25.
 * nio demo:生成10个随机数
 */
public class NioTest1 {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity() ; i++) {

            int randomNumber = new SecureRandom().nextInt(20);
            //写入buffer
            buffer.put(randomNumber);
        }

        //读写状态的翻转
        buffer.flip();

        //读取buffer
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
