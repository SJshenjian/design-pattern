package factory.factoryMethod.impl.pizza;

import factory.factoryMethod.Pizza;

public class ChicagoStyleVeggiePizza extends Pizza {

    public ChicagoStyleVeggiePizza() {
        name = "Chicago Style Deep Dish Veggie Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Veggie");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
