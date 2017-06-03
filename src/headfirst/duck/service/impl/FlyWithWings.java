package headfirst.duck.service.impl;

import headfirst.duck.service.FlyBehavior;

public class FlyWithWings implements FlyBehavior {

	@Override
	public String fly() {
		return "I'm flying!";
	}

}
