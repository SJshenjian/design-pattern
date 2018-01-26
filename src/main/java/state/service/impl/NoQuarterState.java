package state.service.impl;

import state.service.GumballMachine;
import state.service.State;

public class NoQuarterState implements State {
    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("you insert quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("you haven’t inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("you haven’t inserted a quarter");
    }

    @Override
    public void dispense() {
        System.out.println("you haven’t inserted a quarter");
    }
}
