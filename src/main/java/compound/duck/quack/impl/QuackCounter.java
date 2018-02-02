package compound.duck.quack.impl;

import compound.duck.observer.Observer;
import compound.duck.quack.Quackable;

public class QuackCounter implements Quackable {
    private static int numberOfQuack = 0;
    private Quackable duck;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        numberOfQuack++;
    }

    public static int getQuacks() {
        return numberOfQuack;
    }

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        duck.notifyObservers();
    }
}
