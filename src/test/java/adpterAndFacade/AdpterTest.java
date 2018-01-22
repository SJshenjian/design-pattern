package adpterAndFacade;

import adapterAndFacade.adapter.Duck;
import adapterAndFacade.adapter.Turkey;
import adapterAndFacade.adapter.impl.MallardDuck;
import adapterAndFacade.adapter.impl.TurkeyAdapter;
import adapterAndFacade.adapter.impl.WildTurkey;
import org.junit.Test;

public class AdpterTest {

    @Test
    public void testDuckAdapter() {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.fly();

        Turkey wildTurkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(wildTurkey);

        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
