package strategy.service.impl;

import strategy.service.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
	
	@Override
	public String fly() {
		return "I'm flying!";
	}

}
