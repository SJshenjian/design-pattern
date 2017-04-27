package factoryPattern.factoryMethod.test;

import static org.junit.Assert.*;

import org.junit.Test;

import factoryPattern.factoryMethod.ShapeFactory;
import factoryPattern.factoryMethod.service.Shape;
import factoryPattern.factoryMethod.service.impl.factory.CircleFactory;
import factoryPattern.factoryMethod.service.impl.factory.SquareFactory;

public class ShapeFactoryTest {

	@Test
	public void testShapeFactory(){
		ShapeFactory circleFactory=new CircleFactory();
		ShapeFactory squareFactory=new SquareFactory();
		
		Shape circle=circleFactory.getShape();
		Shape square=squareFactory.getShape();
		
		assertEquals("Circle draw",circle.draw());
		assertEquals("Square draw",square.draw());
	}
	
}
