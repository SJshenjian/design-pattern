package factory.abstractFactory.impl;

import factory.abstractFactory.Pizza;
import factory.abstractFactory.PizzaIngredientFactory;

public class CheesePizza extends Pizza {
    private PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        cheese = ingredientFactory.createCheese();
    }
}