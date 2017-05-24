package factoryPattern.abstractFactory.service;

/**
 * 工厂方法模式针对的是一个产品等级结构；而抽象工厂模式则是针对的多个产品等级结构
 */
public abstract class AbstractFactory {
     public abstract Color getColor(String color);
     public abstract Shape getShape(String shape);
}
