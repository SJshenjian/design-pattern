package bridgePattern;

import org.junit.Test;

import bridgePattern.service.Shape;
import bridgePattern.service.impl.Circle;
import bridgePattern.service.impl.GreenCircle;
import bridgePattern.service.impl.RedCircle;

/**
 * 桥接是用于把抽象化与实现化解耦，使得二者可以独立变化。
 * 属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * 主要解决：在有多种可能会变化的情况下，用继承会造成类爆炸问题，扩展起来不灵活。
 *优点： 1、抽象和实现的分离。 2、优秀的扩展能力。 3、实现细节对客户透明。
 *缺点：桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。
 */
public class BridgePatternTest {

	@Test
	public void testBridgePattern(){
		Shape redCircle=new Circle(100,10,10,new RedCircle());
		Shape greenCircle=new Circle(50,10,10,new GreenCircle());
		
		redCircle.draw();
		greenCircle.draw();
	}
}
