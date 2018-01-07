package factory.abstractFactory.impl;

import factory.abstractFactory.Pizza;
import factory.abstractFactory.PizzaIngredientFactory;

public class ClamPizza extends Pizza {
    private PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        cheese = ingredientFactory.createCheese();
        clam = ingredientFactory.createClam();
    }
}