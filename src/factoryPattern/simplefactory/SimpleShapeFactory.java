package factoryPattern.simplefactory;

import factoryPattern.simplefactory.service.Shape;
import factoryPattern.simplefactory.service.impl.Circle;
import factoryPattern.simplefactory.service.impl.Rectangle;
import factoryPattern.simplefactory.service.impl.Square;

public class SimpleShapeFactory {

	/**
	 * 核心:具体的实现由其Shape子类实现,返回其本身Shape对象
	 * 优点:工厂方法getShape为静态方法，可直接调用；
	 *      根据参数获取相应对象，无须知道内部实现；
	 * 缺点:
	 *    增加产品，需改变工厂类逻辑条件，与开闭原则相违背；
	 *    一旦该工厂方法出现故障，其他产品受到影响
	 * @param shapeType
	 * @return
	 */
	public static Shape getShape(String shapeType){
		if(null == shapeType){
			return null;
		}
		
		if("CIRCLE".equalsIgnoreCase(shapeType)){
			return new Circle();
		}else if("Rectangle".equalsIgnoreCase(shapeType)){
			return new Rectangle();
		}else if("Square".equalsIgnoreCase(shapeType)){
			return new Square();
		}
		
		return null;
	}
}
