package factory;

import factory.factoryMethod.Pizza;
import factory.factoryMethod.PizzaStore;
import factory.factoryMethod.impl.store.ChicagoPizzaStore;
import factory.factoryMethod.impl.store.NYPizzaStore;
import org.junit.Test;

public class FactoryMethodTest {
    
    @Test
    public void testFactoryMethod() {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
