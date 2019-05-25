package com.kyrie.protoBuf.netty.client;

import com.kyrie.protoBuf.netty.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {




    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MyDataInfo.MyMessage myMessage = null;

        int randomInt = new Random().nextInt(3);


        if(0== randomInt){

            //发送数据到服务端
            MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                    .setAddress("合肥")
                    .setAge(22).setName("亨利").build();


            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setPerson(person)
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType).build();


        }else if(1 == randomInt){

            MyDataInfo.Address person = MyDataInfo.Address.newBuilder()
                    .setAddressName("hefei").build();

            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setAdd(person)
                    .setDataType(MyDataInfo.MyMessage.DataType.AddressType).build();

        }else{

            MyDataInfo.Order order  = MyDataInfo.Order.newBuilder()
                    .setAddressName("hefei2").build();

            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setOrder(order)
                    .setDataType(MyDataInfo.MyMessage.DataType.OrderType).build();

        }



        ctx.writeAndFlush(myMessage);

    }
}
