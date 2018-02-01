package proxy.remoteProxy.service.impl;

import proxy.remoteProxy.service.GumballMachine;
import proxy.remoteProxy.service.State;

public class SoldOutState implements State {
    private transient GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can’t insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You can’t insert a quarter, the machine is sold out");
    }

    @Override
    public void turnCrank() {
        System.out.println("You can’t insert a quarter, the machine is sold out");
    }

    @Override
    public void dispense() {
        System.out.println("You can’t insert a quarter, the machine is sold out");
    }
}
