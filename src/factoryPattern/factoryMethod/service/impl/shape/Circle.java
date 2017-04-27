package factoryPattern.factoryMethod.service.impl.shape;

import factoryPattern.factoryMethod.service.Shape;

public class Circle implements Shape{

	@Override
	public String draw() {
		return "Circle draw";
	}	
}
