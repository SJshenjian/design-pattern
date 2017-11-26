package headfirst.duck;

import headfirst.duck.service.FlyBehavior;
import headfirst.duck.service.QuackBehavior;

public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public Duck(){		
	}
	
	public abstract String display();//鸭子的外观
	
	public String performFly(){
		return flyBehavior.fly();
	}
	
	public String performQuack(){
		return quackBehavior.quack();
	}
	
	public String swim(){
		return "All ducks float";
	}
}
