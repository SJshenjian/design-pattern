package headfirst.service.impl;

import headfirst.service.QuackBehavior;

/**呱呱叫**/
public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Quack");
	}
}
