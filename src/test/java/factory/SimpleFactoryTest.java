package factory;

import factory.simpleFactory.Pizza;
import factory.simpleFactory.PizzaStore;
import org.junit.Test;

public class SimpleFactoryTest {

    @Test
    public void testSimpleFactory() {
        PizzaStore pizzaStore = new PizzaStore();

        Pizza pizza = pizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = pizzaStore.orderPizza("veggie");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
