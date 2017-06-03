package visitorPattern.service.impl;

import visitorPattern.service.ComputerPartVisitor;

public class ComputerPartDisplayVisitor implements ComputerPartVisitor{

	@Override
	public void visitor(Computer computer) {
		
		System.out.println( "Display computer");
	}

	@Override
	public void visitor(Keyboard keyboard) {
		
		System.out.println("Display keyboard");
	}

	@Override
	public void visitor(Mouse mouse) {
		
		System.out.println("Display mouse");
	}

	@Override
	public void visitor(Monitor monitor) {
		System.out.println("Display monitor");
	}


}
