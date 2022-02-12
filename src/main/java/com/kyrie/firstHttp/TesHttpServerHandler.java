package com.kyrie.firstHttp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 业务处理器
 * Created by Kyrie on 2019/5/18.
 */
public class TesHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Unregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active");
        super.channelActive(ctx);
    }

    /**
     * 连接不活跃 回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel InActive");
        super.channelInactive(ctx);
    }

    /**
     * 连接建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Added");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channel Removed");
        super.handlerRemoved(ctx);
    }

    /**
     * 读取客户端发送的请求，并且响应
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass().getSimpleName());
        //获取远程地址
//        System.out.println(ctx.channel().remoteAddress());

        if(msg instanceof HttpRequest) { //判断时http请求

            HttpRequest httpRequest = (HttpRequest)msg;

            System.out.println("请求方法名" + httpRequest.method().name());
            URI uri= new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){ //请求图标，浏览器会自己发送一次图标的请求
                System.out.println("请求favicon.ico");
                return;
            }
            //响应内容
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            //制定响应协议版本
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            //设置响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //响应返回给客户端
            ctx.writeAndFlush(response);
            //关闭连接
            ctx.channel().close();
        }
    }
}
