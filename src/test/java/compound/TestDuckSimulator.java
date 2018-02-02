package compound;

import compound.duck.factory.AbstractDuckFactory;
import compound.duck.factory.impl.CountingDuckFactory;
import compound.duck.observer.Observer;
import compound.duck.observer.impl.QuackObserver;
import compound.duck.quack.Quackable;
import compound.duck.quack.impl.*;
import org.junit.Test;

public class TestDuckSimulator {

    @Test
    public void DuckSimulatorTest() {
        Flock flock = new Flock();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();

        flock.add(duckFactory.createDuckCall());
        flock.add(duckFactory.createMallardDuck());
        flock.add(duckFactory.createRedheadDuck());
        flock.add(duckFactory.createRubberDuck());
        flock.add(new GooseAdapter(new Goose()));

        Observer quackObserver = new QuackObserver();
        flock.registerObserver(quackObserver);

        simulator(flock);

        System.out.println("The ducks quacked " +
                QuackCounter.getQuacks() + " times");
    }

    private void simulator(Quackable duck) {
        duck.quack();
    }
}
