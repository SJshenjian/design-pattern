package headfirst.service.impl;

import headfirst.service.FlyBehavior;

public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I'm flying!");
	}

}
