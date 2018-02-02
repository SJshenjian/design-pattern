package compound.duck.observer.impl;

import compound.duck.observer.Observable;
import compound.duck.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuackObservable implements Observable {
    private List<Observer> observers = new ArrayList();
    private Observable duck;

    public QuackObservable(Observable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(Observer observer) {
        Objects.requireNonNull(observer);
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.update(duck);
        });
    }
}
