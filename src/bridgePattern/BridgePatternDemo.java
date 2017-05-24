package bridgePattern;

import org.junit.Test;

import bridgePattern.service.Shape;
import bridgePattern.service.impl.Circle;
import bridgePattern.service.impl.GreenCircle;
import bridgePattern.service.impl.RedCircle;

public class BridgePatternDemo {

	@Test
	public void testBridgePattern(){
		Shape redCircle=new Circle(100,10,10,new RedCircle());
		Shape greenCircle=new Circle(50,10,10,new GreenCircle());
		
		redCircle.draw();
		greenCircle.draw();
	}
}
