package strategy;


import strategy.service.FlyBehavior;
import strategy.service.QuackBehavior;

public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public Duck(){		
	}
	
	public abstract String display();
	
	public String performFly(){
		return flyBehavior.fly();
	}
	
	public String performQuack(){
		return quackBehavior.quack();
	}
	
	public String swim(){
		return "All ducks float";
	}

	public void setFlyBehavior(FlyBehavior flyBehavior){
		this.flyBehavior = flyBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior){
		this.quackBehavior = quackBehavior;
	}
}
