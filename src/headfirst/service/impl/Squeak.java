package headfirst.service.impl;

import headfirst.service.QuackBehavior;

/**吱吱叫**/
public class Squeak implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Squeak");
	}
}
