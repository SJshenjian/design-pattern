package strategyPattern.service.impl;

import strategyPattern.service.Strategy;

public class OperationAdd implements Strategy{

	@Override
	public int doOperation(int sum1, int sum2) {
		return sum1+sum2;
	}
	
}
