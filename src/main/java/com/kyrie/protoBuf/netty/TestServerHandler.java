package com.kyrie.protoBuf.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        //接受客户端的Person

        MyDataInfo.MyMessage.DataType type = msg.getDataType();

        if(type == MyDataInfo.MyMessage.DataType.PersonType){
            MyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getAddress());
        }else if(type == MyDataInfo.MyMessage.DataType.AddressType) {
            MyDataInfo.Address address = msg.getAdd();
            System.out.println(address.getAddressName());
        }else{
            MyDataInfo.Order order = msg.getOrder();
            System.out.println(order.getAddressName());
        }

    }



}
