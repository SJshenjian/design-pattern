package strategy.service.impl;

import strategy.service.QuackBehavior;

/**吱吱叫**/
public class Squeak implements QuackBehavior {
	
	@Override
	public String quack() {
		return "Squeak";
	}
}
