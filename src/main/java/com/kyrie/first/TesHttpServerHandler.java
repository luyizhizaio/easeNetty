package com.kyrie.first;

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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel InActive");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Added");
        super.handlerAdded(ctx);
    }

    /**
     * 读取客户端发送的请求，并且响应
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpRequest) { //判断时http请求

            HttpRequest httpRequest = (HttpRequest)msg;

            System.out.println("请求方法名" + httpRequest.method().name());
            URI uri= new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){ //请求图表
                System.out.println("请求favicon.ico");
                return;
            }

            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);
        }




    }
}
