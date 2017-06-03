package visitorPattern;

import org.junit.Test;

import visitorPattern.service.ComputerPart;
import visitorPattern.service.impl.Computer;
import visitorPattern.service.impl.ComputerPartDisplayVisitor;

public class VisitorPatternDemo {

	@Test
	public void testVisitorPattern(){
		
		ComputerPart computer=new Computer();
		
		computer.accept(new ComputerPartDisplayVisitor());
	}
}
