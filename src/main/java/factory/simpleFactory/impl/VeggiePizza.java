package factory.simpleFactory.impl;

import factory.Pizza;

public class VeggiePizza extends Pizza {

    public VeggiePizza() {
        name = "Deep Dish Veggie Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }
}
