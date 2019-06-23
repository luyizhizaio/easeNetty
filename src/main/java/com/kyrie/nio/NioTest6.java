package com.kyrie.nio;

import java.nio.ByteBuffer;

/**
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for(int i =0; i< buffer.capacity(); ++i){
            buffer.put((byte)i);//int强转成字节
        }

        //slice方法截取buffer 中2-6位置的数据
        buffer.position(2);
        buffer.limit(6);
        ByteBuffer sliceBuffer = buffer.slice();

        //修改sliceBuffer中的数据
        for(int i = 0; i< sliceBuffer.capacity(); ++i){
            byte b = sliceBuffer.get(i);
            b *=2; //byte 乘以2
            sliceBuffer.put(i,b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        //遍历buffer
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }


    }

}
