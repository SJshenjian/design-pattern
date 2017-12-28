package duck.service.impl;

import duck.Duck;
import duck.service.FlyBehavior;
import duck.service.QuackBehavior;

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
