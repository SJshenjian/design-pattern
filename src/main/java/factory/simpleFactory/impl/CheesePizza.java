package factory.simpleFactory.impl;

import factory.Pizza;

public class CheesePizza extends Pizza {

    public CheesePizza() {
        name = "Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }
}
