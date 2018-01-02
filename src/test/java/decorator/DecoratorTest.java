package decorator;

import decorator.service.Beverage;
import decorator.service.impl.coffee.DarkRoast;
import decorator.service.impl.coffee.Espresso;
import decorator.service.impl.condiment.Mocha;
import decorator.service.impl.condiment.Soy;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class DecoratorTest {

    @Test
    public void testDecorator() {
        Beverage espresso = new Espresso();
        assertEquals("1.99", String.valueOf(espresso.cost()));
        assertEquals("Espresso Coffee", espresso.getDescription());

        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Soy(darkRoast);
        assertEquals("1.5899999999999999", String.valueOf(darkRoast.cost()));
        assertEquals("Dark Roast Coffee,Mocha,Mocha,Soy", darkRoast.getDescription());
    }
}
