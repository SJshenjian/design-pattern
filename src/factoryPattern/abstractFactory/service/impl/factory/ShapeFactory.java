package factoryPattern.abstractFactory.service.impl.factory;

import factoryPattern.abstractFactory.service.AbstractFactory;
import factoryPattern.abstractFactory.service.Color;
import factoryPattern.abstractFactory.service.Shape;
import factoryPattern.abstractFactory.service.impl.shape.Circle;
import factoryPattern.abstractFactory.service.impl.shape.Rectangle;
import factoryPattern.abstractFactory.service.impl.shape.Square;

public class ShapeFactory extends AbstractFactory  {


	@Override
	public Shape getShape(String shape) {
	    if(null == shape){
	    	return null;
	    }
	    if("Circle".equalsIgnoreCase(shape)){
	    	return new Circle();
	    }else if("Square".equalsIgnoreCase(shape)){
	    	return new Square();
	    }else if("Rectangle".equalsIgnoreCase(shape)){
	    	return new Rectangle();
	    }
	    
		return null;
	}
  
	@Override
	public Color getColor(String color) {
		return null;
	}
}
