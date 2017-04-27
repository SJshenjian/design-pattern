package factoryPattern.simplefactory.service.impl;

import factoryPattern.simplefactory.service.Shape;

public class Circle implements Shape{

	@Override
	public String draw() {
		return "Circle draw";
	}	
}
