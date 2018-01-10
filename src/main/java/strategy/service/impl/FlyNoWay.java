package strategy.service.impl;

import strategy.service.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
	
	@Override
	public String fly() {
		return "I can't fly";
	}
}
