package headfirst;

import headfirst.service.FlyBehavior;
import headfirst.service.QuackBehavior;

public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public Duck(){		
	}
	
	public abstract void display();//鸭子的外观
	
	public void performFly(){
		flyBehavior.fly();
	}
	
	public void performQuack(){
		quackBehavior.quack();
	}
	
	public void swim(){
		System.out.println("All ducks float");
	}
}
