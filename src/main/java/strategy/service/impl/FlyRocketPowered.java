package strategy.service.impl;

import strategy.service.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
    
    @Override
    public String fly() {
        return "I'm flying with a rocket";
    }
}
