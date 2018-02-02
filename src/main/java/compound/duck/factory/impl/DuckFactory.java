package compound.duck.factory.impl;

import compound.duck.factory.AbstractDuckFactory;
import compound.duck.quack.Quackable;
import compound.duck.quack.impl.DuckCall;
import compound.duck.quack.impl.MallardDuck;
import compound.duck.quack.impl.RedheadDuck;
import compound.duck.quack.impl.RubberDuck;

public class DuckFactory implements AbstractDuckFactory {

    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
