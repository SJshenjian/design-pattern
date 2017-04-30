package factoryPattern.factoryMethod;

import factoryPattern.factoryMethod.service.Shape;

/*
 * 创建接口，同种产品的不同系列分别实现该工厂接口(比如三角形、矩形、椭圆均同 为形状产品)。
 * 消费产品系列时只需调用该系列的工厂方法生产产品就可以了，这样避免了简单工厂方法的缺点:
 * 一旦该工厂方法出现故障，其他产品受到影响 
 */
public interface ShapeFactory {

	public  Shape getShape();
}
