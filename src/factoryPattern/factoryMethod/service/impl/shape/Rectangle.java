package factoryPattern.factoryMethod.service.impl.shape;

import factoryPattern.factoryMethod.service.Shape;

public class Rectangle implements Shape {

	@Override
	public String draw() {
		return "Rectangle draw";
	}
	
}
