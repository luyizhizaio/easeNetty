package com.kyrie.decorator;

/**
 * Created by Kyrie on 2019/5/25.
 * 装饰角色
 */
public class Decorator implements Component {
    private Component component;

    public  Decorator(Component component){
        this.component = component;
    }

    @Override
    public void doSomeThing() {
        component.doSomeThing();
    }
}
