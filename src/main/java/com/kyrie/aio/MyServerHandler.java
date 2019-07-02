package com.kyrie.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kyrie on 2019/6/29.
 *  CompletionHandler ：A handler for consuming the result of an asynchronous I/O operation.
 */
public class MyServerHandler implements CompletionHandler<AsynchronousSocketChannel,MyServer> {
    private final Integer BUUFFER_SIZE=1024;
    @Override
    public void completed(AsynchronousSocketChannel result, MyServer attachment) {

        attachment.asynServerSocketChannel.accept(attachment,this);
        read(result);


    }

    private void read(AsynchronousSocketChannel asynSocketChannel) {

       ByteBuffer byteBuffer = ByteBuffer.allocate(BUUFFER_SIZE);

        //Reads a sequence of bytes from this channel into the given buffer.
        asynSocketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            //attachment：为接受到的数据
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                String resultData = new String(attachment.array()).trim();
                System.out.println("server receive message: " + resultData);
                String response = resultData +"= X" ;
                write(asynSocketChannel,response);


            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });

    }

    private void write(AsynchronousSocketChannel asynSocketChannel, String response) {

        try {
            ByteBuffer buf = ByteBuffer.allocate(BUUFFER_SIZE);
            buf.put(response.getBytes());
            buf.flip();
            asynSocketChannel.write(buf).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, MyServer attachment) {
        exc.printStackTrace();
    }
}
