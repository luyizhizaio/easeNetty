package com.kyrie.protoBuf.basedemo;

public class MainTest {

  public static void main(String[] args) throws Exception{
    DataInfo.Student stud = DataInfo.Student.newBuilder()
      .setName("小柚")
      .setAge(2)
      .setAddress("北京").build();
    //对象转成字节数组,字节数组可用于网络传输
    byte[] bytes = stud.toByteArray();

    //字节转成对象
    DataInfo.Student stud2 = DataInfo.Student.parseFrom(bytes);

    System.out.println(stud2.getName());
  }
}
