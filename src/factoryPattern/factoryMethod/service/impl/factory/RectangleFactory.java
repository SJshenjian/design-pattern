package factoryPattern.factoryMethod.service.impl.factory;

import factoryPattern.factoryMethod.ShapeFactory;
import factoryPattern.factoryMethod.service.Shape;
import factoryPattern.factoryMethod.service.impl.shape.Rectangle;

public class RectangleFactory implements ShapeFactory{

	@Override
	public Shape getShape() {
		
		return new Rectangle();
	}

}
