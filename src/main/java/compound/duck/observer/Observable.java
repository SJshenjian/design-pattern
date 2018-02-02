package compound.duck.observer;

public interface Observable {

    void registerObserver(Observer observer);

    void notifyObservers();
}
