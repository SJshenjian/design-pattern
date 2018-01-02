package factory.simpleFactory;

import factory.Pizza;
import factory.simpleFactory.impl.CheesePizza;
import factory.simpleFactory.impl.VeggiePizza;

public class SimplePizzaFactory {

    public static Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }
}