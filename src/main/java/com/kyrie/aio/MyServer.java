package com.kyrie.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 负责创建服务器通道，绑定端口，等待请求。
 * Created by Kyrie on 2019/6/29.
 */
public class MyServer {

    //线程池
    private ExecutorService executorService;
    //通道组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    public AsynchronousServerSocketChannel asynServerSocketChannel;

    public void start(int port) throws Exception {
        //创建一个线程池
        executorService = Executors.newCachedThreadPool();

        //创建通道组
        threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
        //创建服务器通道
        asynServerSocketChannel = AsynchronousServerSocketChannel.open(threadGroup);
        //绑定端口
        asynServerSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("server start,bind port:"+port);

        //异步 等待客户端连接，
        asynServerSocketChannel.accept(this,new MyServerHandler());

        // 一直阻塞 不让服务器停止，真实环境是在tomcat下运行，所以不需要这行代码
        Thread.sleep(Integer.MAX_VALUE);


    }


    public static void main(String[] args) throws Exception {

        new MyServer().start(8899);

    }

}
