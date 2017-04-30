package factoryPattern.abstractFactory.service.impl.shape;

import factoryPattern.abstractFactory.service.Shape;

public class Rectangle implements Shape {

	@Override
	public String draw() {
		return "Rectangle draw";
	}

}
