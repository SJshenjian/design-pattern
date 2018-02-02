package compound.duck.observer.impl;

import compound.duck.observer.Observer;
import compound.duck.observer.Observable;

public class QuackObserver implements Observer {
    @Override
    public void update(Observable duck) {
        System.out.println("Quackologist: " + duck + " just quacked.");
    }
}
