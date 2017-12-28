package duck.service.impl;

import duck.service.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
	
	@Override
	public String fly() {
		return "I'm flying!";
	}

}
