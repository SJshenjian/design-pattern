package factoryPattern.abstractFactory.service.impl.shape;

import factoryPattern.abstractFactory.service.Shape;

public class Square implements Shape{

	@Override
	public String draw() {
		
		return "Square draw";
	}

}
