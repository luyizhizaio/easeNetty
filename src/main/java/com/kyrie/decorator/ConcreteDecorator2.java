package com.kyrie.decorator;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomeThing() {
        super.doSomeThing();
        this.doAnotherThing();
    }

    private void doAnotherThing(){
        System.out.println("功能3");
    }
}
