package factoryPattern.simplefactory.service.impl;

import factoryPattern.simplefactory.service.Shape;

public class Rectangle implements Shape {

	@Override
	public String draw() {
		return "Rectangle draw";
	}
	
}
