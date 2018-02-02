package compound.duck.factory;

import compound.duck.quack.Quackable;

public interface AbstractDuckFactory {
    Quackable createMallardDuck();

    Quackable createRedheadDuck();

    Quackable createDuckCall();

    Quackable createRubberDuck();
}
