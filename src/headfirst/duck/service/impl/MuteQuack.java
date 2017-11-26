package headfirst.duck.service.impl;

import headfirst.duck.service.QuackBehavior;

/**安静**/
public class MuteQuack implements QuackBehavior {

	@Override
	public String quack() {
		return "Silence";
	}

}
