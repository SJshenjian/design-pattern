package compound.duck.quack;

import compound.duck.observer.Observable;

public interface Quackable extends Observable {
    void quack();
}
