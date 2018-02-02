package compound.duck.observer;

import compound.duck.observer.impl.QuackObservable;

public interface Observer {
    void update(Observable duck);
}
