package com.kyrie.decorator;

/**
 * Created by Kyrie on 2019/5/25.
 * 具体构建角色
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomeThing() {
        System.out.println("功能1");
    }
}
