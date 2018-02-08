package java8;

import java8.chap1to5.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class StreamDishTest {

    @Test
    public void testStreamDish() {
        List<String> threeHighCaloricDishNames = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(threeHighCaloricDishNames);

        //flatmap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0 )
                        .map(j -> new int[]{i,j}))
                .collect(toList());

        //
        Optional<Dish> dish = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        dish.ifPresent(d -> System.out.println(d.getName()));
    }
}
