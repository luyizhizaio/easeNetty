package com.kyrie.thrift.client;

import com.kyrie.thrift.Person;
import com.kyrie.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.layered.TFramedTransport;

public class ThriftClient {

  public static void main(String[] args) throws Exception {

    TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);

    TCompactProtocol protocol = new TCompactProtocol(transport);

    PersonService.Client client = new PersonService.Client(protocol);

    try{
      //打开链接
      transport.open();

      //调用 方法
      Person person = client.getPersonByUsername("笑了");
      System.out.println(person.getUsername());
      System.out.println(person.getAge());
      System.out.println(person.isMarried());

      System.out.println("===================");

      Person person1 = new Person();
      person1.setUsername("nihao");
      person1.setAge(3);
      person1.setMarried(false);

      client.savePerson(person1);

    }catch (Exception e){
      throw  new RuntimeException(e.getMessage(),e);
    }finally {
      transport.close();
    }
  }
}
