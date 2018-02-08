package java8;

import java8.chap1to5.Apple;
import java8.chap1to5.FilteringApples;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilteringApplesTest {

    @Test
    public void testFilterApples() {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples = FilteringApples.filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        List<Apple> heavyApples = FilteringApples.filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        System.out.println("\n-----------using lambda-----------------");
        List<Apple> greenApples2 = FilteringApples.filterApples(inventory, apple -> "green".equalsIgnoreCase(apple.getColor()) );
        System.out.println(greenApples2);

        List<Apple> heavyApples2 = FilteringApples.filterApples(inventory, apple -> apple.getWeight() > 150);
        System.out.println(heavyApples2);

        inventory.stream().filter(apple -> apple.getWeight() > 150).collect(toList());

        inventory.parallelStream().filter(apple -> apple.getWeight() > 150).collect(toList());

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //方法引用简化lambda
        inventory.sort(Comparator.comparing(Apple::getWeight));

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
    }
}