package headfirst.duck.service.impl;

import headfirst.duck.service.QuackBehavior;

/**吱吱叫**/
public class Squeak implements QuackBehavior {

	@Override
	public String quack() {
		return "Squeak";
	}
}
