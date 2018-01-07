package factory.factoryMethod.impl.store;

import factory.factoryMethod.Pizza;
import factory.factoryMethod.PizzaStore;
import factory.factoryMethod.impl.pizza.ChicagoStyleCheesePizza;
import factory.factoryMethod.impl.pizza.ChicagoStyleVeggiePizza;

public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        if (item.equals("veggie")) {
            return new ChicagoStyleVeggiePizza();
        }
        return null;
    }
}
