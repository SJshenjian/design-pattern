package factoryPattern.abstractFactory.test;

import static org.junit.Assert.*;

import org.junit.Test;

import factoryPattern.abstractFactory.service.AbstractFactory;
import factoryPattern.abstractFactory.service.Color;
import factoryPattern.abstractFactory.service.Shape;
import factoryPattern.abstractFactory.service.impl.factory.ColorFactory;
import factoryPattern.abstractFactory.service.impl.factory.ShapeFactory;

/*
 * 测试类
 */
public class AbstractFactoryTest {

	@Test
	public void testFillColor(){
		AbstractFactory colorFactory=new ColorFactory();
		Color red=colorFactory.getColor("Red");
		assertEquals("Red fill",red.fill());
	}
	
	@Test
	public void testDrawShape(){
		AbstractFactory shapeFactory=new ShapeFactory();
		Shape circle=shapeFactory.getShape("Circle");
		assertEquals("Circle draw",circle.draw());
	}
}
