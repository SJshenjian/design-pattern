package factory.abstractFactory.impl;

import factory.abstractFactory.Cheese;
import factory.abstractFactory.Clam;
import factory.abstractFactory.PizzaIngredientFactory;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Clam createClam() {
        return new FreshClam();
    }
}
