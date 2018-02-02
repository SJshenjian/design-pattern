package compound.duck.quack.impl;

import compound.duck.observer.Observer;
import compound.duck.observer.Observable;
import compound.duck.observer.impl.QuackObservable;
import compound.duck.quack.Quackable;

import java.util.ArrayList;
import java.util.List;

public class Flock implements Quackable{
    private List<Quackable> ducks  = new ArrayList();

    @Override
    public void quack() {
        ducks.forEach(duck -> {
            duck.quack();
        });
    }

    public void add(Quackable duck) {
        ducks.add(duck);
    }

    @Override
    public void registerObserver(Observer observer) {
        ducks.forEach(duck -> {
            duck.registerObserver(observer);
        });
    }

    @Override
    public void notifyObservers() {

    }
}
