package templateMethod;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import templateMethod.service.CaffeineBeverage;
import templateMethod.service.CaffeineBeverageWithHook;
import templateMethod.service.impl.Coffee;
import templateMethod.service.impl.CoffeeWithHook;
import templateMethod.service.impl.Tea;
import templateMethod.service.impl.TeaWithHook;

public class templateMethodTest {

    @Test
    public void testTemplateMethod() {
        CaffeineBeverage tea = new Tea();
        CaffeineBeverage coffee = new Coffee();

        System.out.println("\nMaking tea");
        tea.prepareRecipe();

        System.out.println("\nMaking coffee");
        coffee.prepareRecipe();
    }

    @Test
    public void testTemplateMethodWithHook() {
        CaffeineBeverageWithHook coffee = new CoffeeWithHook();
        CaffeineBeverageWithHook tea = new TeaWithHook();

        System.out.println("\nMaking tea");
        tea.prepareRecipe();

        System.out.println("\nMaking Coffee");
        coffee.prepareRecipe();
    }
}
