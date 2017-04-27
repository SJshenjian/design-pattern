package factoryPattern.simplefactory.service.impl;

import factoryPattern.simplefactory.service.Shape;

public class Square implements Shape{

	@Override
	public String draw() {
		return "Square draw";
	}
}
