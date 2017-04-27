package factoryPattern.simplefactory;

import factoryPattern.simplefactory.service.Shape;
import factoryPattern.simplefactory.service.impl.Circle;
import factoryPattern.simplefactory.service.impl.Rectangle;
import factoryPattern.simplefactory.service.impl.Square;

public class SimpleShapeFactory {

	public static Shape getShape(String shapeType){
		if(null == shapeType){
			return null;
		}
		
		if("CIRCLE".equalsIgnoreCase(shapeType)){
			return new Circle();
		}else if("Rectangle".equalsIgnoreCase(shapeType)){
			return new Rectangle();
		}else if("Square".equalsIgnoreCase(shapeType)){
			return new Square();
		}
		
		return null;
	}
}
