package strategy.service.impl;

import strategy.service.QuackBehavior;

/**呱呱叫**/
public class Quack implements QuackBehavior {
	
	@Override
	public String quack() {
		return "Quack";
	}
}
