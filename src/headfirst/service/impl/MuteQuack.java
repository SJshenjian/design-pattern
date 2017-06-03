package headfirst.service.impl;

import headfirst.service.QuackBehavior;

/**安静**/
public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Silence");
	}

}
