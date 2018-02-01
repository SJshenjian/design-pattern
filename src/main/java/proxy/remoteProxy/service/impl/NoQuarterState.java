package proxy.remoteProxy.service.impl;

import proxy.remoteProxy.service.GumballMachine;
import proxy.remoteProxy.service.State;

public class NoQuarterState implements State {
    private transient GumballMachine gumballMachine;

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
