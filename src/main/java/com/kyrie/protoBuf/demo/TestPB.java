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

        //����ת���ֽ�����
        byte[] bytes = person.toByteArray();
        Person person2 = Person.parseFrom(bytes);

        System.out.println(person2);
    }



}
