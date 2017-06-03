package headfirst;

import headfirst.service.impl.FlyWithWings;
import headfirst.service.impl.Quack;

public class MallardDuck extends Duck{

	public MallardDuck(){
		flyBehavior=new FlyWithWings();
		quackBehavior=new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("I'm a real MallardDuck");		
	}
}
