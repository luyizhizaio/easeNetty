package com.kyrie.decorator;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(
                new ConcreteDecorator1(
                        new ConcreteComponent()));

        component.doSomeThing();
    }
}
