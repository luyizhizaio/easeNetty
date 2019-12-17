package com.kyrie.protoBuf.demo;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kyrie.protoBuf.demo.AddressBookProtos.Person;

/**
 * Created by Kyrie on 2019/5/23.
 */
public class TestPB {

    public static void main(String args[]) throws InvalidProtocolBufferException {

        Person person = Person.newBuilder()
                .setEmail("")
                .setId(122)
                .setName("xxx").build();

        System.out.println(person.toString());

        //对象转成字节数组
        byte[] bytes = person.toByteArray();
        //从字节数组解析对象
        Person person2 = Person.parseFrom(bytes);

        System.out.println(person2);
    }



}
