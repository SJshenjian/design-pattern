package headfirst.service.impl;

import headfirst.service.FlyBehavior;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I can't fly");
	}
}
