package factory.factoryMethod.impl.store;

import factory.Pizza;
import factory.factoryMethod.PizzaStore;
import factory.factoryMethod.impl.pizza.NYStyleCheesePizza;
import factory.factoryMethod.impl.pizza.NYStyleVeggiePizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        }
        if (item.equals("veggie")) {
            return new NYStyleVeggiePizza();
        }
        return null;
    }
}