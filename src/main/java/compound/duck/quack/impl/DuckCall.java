package compound.duck.quack.impl;

import compound.duck.observer.Observable;
import compound.duck.observer.Observer;
import compound.duck.observer.impl.QuackObservable;
import compound.duck.quack.Quackable;

public class DuckCall implements Quackable {
    private Observable observable;

    public DuckCall() {
        observable = new QuackObservable(this);
    }

    @Override
    public void quack() {
        System.out.println("Kwak");
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
