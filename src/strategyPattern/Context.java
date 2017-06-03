package strategyPattern;

import strategyPattern.service.Strategy;

public class Context {

	private Strategy strategy;
	
	public Context(Strategy strategy){
		this.strategy=strategy;
	}
	
	public int excuteStrategy(int sum1,int sum2){
		return strategy.doOperation(sum1, sum2);
	}
}
