package duck;

import static org.junit.Assert.*;

import strategy.service.impl.FlyRocketPowered;
import strategy.service.impl.ModelDuck;
import org.junit.Test;

import strategy.Duck;
import strategy.MallardDuck;

public class MallarDuckTest {

    @Test
    public void test() {
        Duck mallarDuck = new MallardDuck();
        assertEquals("I'm a real MallardDuck", mallarDuck.display());
        assertEquals("I'm flying!", mallarDuck.performFly());
        assertEquals("Quack", mallarDuck.performQuack());
    }

    @Test
    public void testDuckBehaviorChange() {
        Duck modelDuck = new ModelDuck();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        assertEquals("I'm flying with a rocket", modelDuck.performFly());
    }

}
