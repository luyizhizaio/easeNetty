package com.kyrie.nio;

import java.nio.ByteBuffer;

/**
 * 类型化的put和get方法
 * Created by Kyrie on 2019/6/23.
 */
public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(10);
        buffer.putLong(59333333333333333L);
        buffer.putDouble(1.23d);
        buffer.putChar('应');
        buffer.putShort((short)2);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());

    }

}
