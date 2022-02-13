package com.kyrie.thrift.server;

import com.kyrie.thrift.DataException;
import com.kyrie.thrift.Person;
import com.kyrie.thrift.PersonService;
import org.apache.thrift.TException;

/**
 * 实现接口
 */
public class PersonServiceImpl implements PersonService.Iface {
  @Override
  public Person getPersonByUsername(String username) throws DataException, TException {
    System.out.println("get client param:"+ username);
    Person person = new Person();
    person.setAge(1);
    person.setUsername("定义");
    person.setMarried(true);

    return person;
  }

  @Override
  public void savePerson(Person person) throws DataException, TException {
    System.out.println("get client request");
    System.out.println(person.getUsername());
    System.out.println(person.getAge());
    System.out.println(person.isMarried());

  }
}
