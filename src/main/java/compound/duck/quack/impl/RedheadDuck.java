package compound.duck.quack.impl;

import compound.duck.observer.Observer;
import compound.duck.observer.Observable;
import compound.duck.observer.impl.QuackObservable;
import compound.duck.quack.Quackable;

public class RedheadDuck implements Quackable {
    private Observable observable;

    public RedheadDuck() {
        observable = new QuackObservable(this);
    }

    @Override
    public void quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
