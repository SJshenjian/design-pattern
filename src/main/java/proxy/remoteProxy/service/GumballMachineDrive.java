package proxy.remoteProxy.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class GumballMachineDrive {
    private Map<String, GumballMachine> machineService;

    public GumballMachineDrive() {
        machineService = new HashMap<>();
    }

    public void registry() {
        try {
            machineService.put("www.alibaba.com", new GumballMachine("www.alibaba.com", 100));
            machineService.put("www.baidu.com", new GumballMachine("www.baidu.com", 200));
            machineService.put("www.qq.com", new GumballMachine("www.qq.com", 300));

            machineService.forEach(
                    (key, value) -> {
                        try {
                            Naming.rebind(key,value);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GumballMachineDrive().registry();
    }
}
