package strategyPattern;

import static org.junit.Assert.*;

import org.junit.Test;

import strategyPattern.service.Strategy;
import strategyPattern.service.impl.OperationAdd;
import strategyPattern.service.impl.OperationMultiply;
import strategyPattern.service.impl.OperationSubstract;

public class StrategyPatternDemo {

	@Test
	public void testStrategy(){
		
	    Context context1=new Context(new OperationAdd());
	    assertEquals(10,context1.excuteStrategy(1, 9));
	    
	    Context context2=new Context(new OperationSubstract());
	    assertEquals(-1,context2.excuteStrategy(0, 1));
	    
	    Context context3=new Context(new OperationMultiply());
	    assertEquals(0,context2.excuteStrategy(0, 1));
	}
}
