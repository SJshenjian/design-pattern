package duck;

import static org.junit.Assert.*;

import duck.service.impl.FlyRocketPowered;
import duck.service.impl.ModelDuck;
import org.junit.Test;

import duck.Duck;
import duck.MallardDuck;

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
