package factory;

import factory.abstractFactory.PizzaStore;
import factory.abstractFactory.impl.NYPizzaStore;
import org.junit.Test;

public class AbstractFactoryTest {

    @Test
    public void testAbstractFactory() {
        PizzaStore pizzaStore = new NYPizzaStore();
        pizzaStore.orderPizza("cheese");
    }
}
