package factoryPattern.abstractFactory.service.impl.factory;

import factoryPattern.abstractFactory.service.AbstractFactory;
import factoryPattern.abstractFactory.service.Color;
import factoryPattern.abstractFactory.service.Shape;
import factoryPattern.abstractFactory.service.impl.color.Black;
import factoryPattern.abstractFactory.service.impl.color.Red;
import factoryPattern.abstractFactory.service.impl.color.White;


public class ColorFactory extends AbstractFactory {

	@Override
	public Color getColor(String color) {
	
		if(null == color){
			return null;
		}
		if("Red".equalsIgnoreCase(color)){
			return new Red();
		}else if("Black".equalsIgnoreCase(color)){
			return new Black();
		}else if("White".equalsIgnoreCase(color)){
			return new White();
		}
		return null;
	}

	@Override
	public Shape getShape(String shape) {

		return null;
	}
}
