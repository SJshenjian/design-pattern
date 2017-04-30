package factoryPattern.abstractFactory.service.impl.color;

import factoryPattern.abstractFactory.service.Color;


public class White implements Color{

	@Override
	public String fill() {
		return "White fill";
	}

}
