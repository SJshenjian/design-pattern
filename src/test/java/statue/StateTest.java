package statue;

import org.junit.Test;
import state.service.GumballMachine;

import java.util.Random;

public class StateTest {

    @Test
    public void testState() {
        GumballMachine gumballMachine = new GumballMachine(5);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
    }
}
