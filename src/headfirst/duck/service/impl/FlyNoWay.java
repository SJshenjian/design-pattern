package headfirst.duck.service.impl;

import headfirst.duck.service.FlyBehavior;

public class FlyNoWay implements FlyBehavior {

	@Override
	public String fly() {
		return "I can't fly";
	}
}
