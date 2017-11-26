package duck;


import duck.service.impl.FlyWithWings;
import duck.service.impl.Quack;

public class MallardDuck extends Duck{

	public MallardDuck(){
		flyBehavior=new FlyWithWings();
		quackBehavior=new Quack();
	}
	
	@Override
	public String display() {
		return "I'm a real MallardDuck";		
	}
}
