package com.kyrie.decorator;

/**
 * Created by Kyrie on 2019/5/25.
 */
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomeThing() {
        super.doSomeThing();
        this.doAnotherThing();
    }

    /**
     * 提供额外的功能
     */
    private void doAnotherThing(){
        System.out.println("功能2");
    }
}
