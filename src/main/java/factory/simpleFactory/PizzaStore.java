package factory.simpleFactory;

import factory.Pizza;

public class PizzaStore {

    public PizzaStore() {};

    public Pizza orderPizza(String type) {
        Pizza pizza = SimplePizzaFactory.createPizza(type); //没开分店的奥，简单工厂完全满足要求的啦
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
