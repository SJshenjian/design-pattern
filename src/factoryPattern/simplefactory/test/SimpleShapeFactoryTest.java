package factoryPattern.simplefactory.test;

import static org.junit.Assert.*;

import org.junit.Test;

import factoryPattern.simplefactory.SimpleShapeFactory;
import factoryPattern.simplefactory.service.Shape;



public class SimpleShapeFactoryTest {

	@Test
	public void testSimpleShapeFactory(){
		
		Shape circle=SimpleShapeFactory.getShape("Circle");
		Shape rectangle=SimpleShapeFactory.getShape("Rectangle");
		Shape square=SimpleShapeFactory.getShape("Square");
		
		assertEquals("Circle draw",circle.draw());
		assertEquals("Rectangle draw",rectangle.draw());
		assertEquals("Square draw",square.draw());
		
	}
}
