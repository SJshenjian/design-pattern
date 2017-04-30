package factoryPattern.abstractFactory.service.impl.color;

import factoryPattern.abstractFactory.service.Color;

public class Red implements Color{

	@Override
	public String fill() {
		return "Red fill";
	}

}
