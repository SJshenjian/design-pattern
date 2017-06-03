package visitorPattern.service.impl;

import visitorPattern.service.ComputerPart;
import visitorPattern.service.ComputerPartVisitor;

public class Computer implements ComputerPart {

	ComputerPart[] parts;
	
	public Computer(){
		parts=new ComputerPart[]{new Mouse(),new Keyboard(),new Monitor()};
	}
	
	@Override
	public void accept(ComputerPartVisitor visitor) {
		
		for(int i=0;i<parts.length;i++){
			parts[i].accept(visitor);
		}
		
		visitor.visitor(this);
	}

}
