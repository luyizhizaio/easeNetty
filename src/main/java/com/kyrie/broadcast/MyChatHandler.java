package com.kyrie.broadcast;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 *
 * Created by Kyrie on 2019/5/18.
 */
public class MyChatHandler extends SimpleChannelInboundHandler<String> {


    //用于保存channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 任一客户端
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();


        channelGroup.forEach(ch ->{

            if(channel !=ch){
                ch.writeAndFlush(channel.remoteAddress() +" 发送的消息："+ msg+ "\n");
            }else{
                ch.writeAndFlush("[自己]"+msg+"\n");
            }

        });




    }


    /**
     * 客户端与服务端 建立连接【回调函数】
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        //连接对象
        Channel channel = ctx.channel();
        //对该组中所有channel发送消息
        channelGroup.writeAndFlush(channel.remoteAddress() +"加入\n");


        //客户端连接保存到channelGroup中
        channelGroup.add(channel);


    }

    /**
     * 客户端与服务端 连接断开【回调函数】
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //断开的连接,NETTY自动从group移除该channel
        Channel channel = ctx.channel();

        System.out.println("[服务器]-" + channel.remoteAddress() +"离开\n");

        System.out.println("group size:"+channelGroup.size());

    }

    /**
     * 连接为活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println("[服务器]-" + channel.remoteAddress() +"上线\n");
    }

    /**
     * 连接为非活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() +"下线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
