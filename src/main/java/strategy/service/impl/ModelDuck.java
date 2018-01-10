package strategy.service.impl;

import strategy.Duck;
import strategy.service.FlyBehavior;
import strategy.service.QuackBehavior;

public class ModelDuck extends Duck{
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public ModelDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public String display() {
        return "I'm a model duck";
    }
}
