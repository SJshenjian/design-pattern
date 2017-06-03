package visitorPattern.service.impl;

import visitorPattern.service.ComputerPart;
import visitorPattern.service.ComputerPartVisitor;

public class Keyboard implements ComputerPart {

	@Override
	public void accept(ComputerPartVisitor visitor) {
		 visitor.visitor(this);
	}

}
