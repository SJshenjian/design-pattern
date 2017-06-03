package visitorPattern.service.impl;

import visitorPattern.service.ComputerPart;
import visitorPattern.service.ComputerPartVisitor;

public class Monitor implements ComputerPart {

	@Override
	public void accept(ComputerPartVisitor visitor) {
		 visitor.visitor(this);
	}

}
