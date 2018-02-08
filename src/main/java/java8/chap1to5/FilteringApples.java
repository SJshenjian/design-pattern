package java8.chap1to5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList();

        inventory.forEach(apple -> {
            if(p.test(apple)) {
                result.add(apple);
            }
        });

        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
