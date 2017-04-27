package factoryPattern.factoryMethod.service.impl.shape;

import factoryPattern.factoryMethod.service.Shape;

public class Square implements Shape{

	@Override
	public String draw() {
		return "Square draw";
	}
}
