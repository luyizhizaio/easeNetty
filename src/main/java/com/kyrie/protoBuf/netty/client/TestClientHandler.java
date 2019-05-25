package com.kyrie.protoBuf.netty.client;

import com.kyrie.protoBuf.netty.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {




    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //�������ݵ������
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setAddress("�Ϸ�")
                .setAge(22).setName("����").build();

        ctx.writeAndFlush(person);

    }
}
