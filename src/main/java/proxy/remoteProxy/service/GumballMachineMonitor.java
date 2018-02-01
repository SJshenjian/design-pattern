package proxy.remoteProxy.service;

import java.rmi.RemoteException;

public class GumballMachineMonitor {
    private GumballMachineRemote machine;

    public GumballMachineMonitor(GumballMachineRemote machine) {
        this.machine = machine;
    }

    public void report() {
        try {
            System.out.println("Gumball Machine: " + machine.getLocation());
            System.out.println("Current inventory: " + machine.getCount() + " gumballs");
            System.out.println("Current state: " + machine.getState());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
