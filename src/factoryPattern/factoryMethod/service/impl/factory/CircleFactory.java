package factoryPattern.factoryMethod.service.impl.factory;

import factoryPattern.factoryMethod.ShapeFactory;
import factoryPattern.factoryMethod.service.Shape;
import factoryPattern.factoryMethod.service.impl.shape.Circle;

public class CircleFactory implements ShapeFactory{

	@Override
	public Shape getShape() {
		
		return new Circle();
	}

}
