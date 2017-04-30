package factoryPattern.abstractFactory.service.impl.shape;

import factoryPattern.abstractFactory.service.Shape;

public class Circle implements Shape {

	@Override
	public String draw() {
		return "Circle draw";
	}

}
