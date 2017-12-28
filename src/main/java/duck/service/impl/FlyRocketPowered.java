package duck.service.impl;

import duck.service.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
    
    @Override
    public String fly() {
        return "I'm flying with a rocket";
    }
}
