package headfirst.test;

import org.junit.Test;

import headfirst.Duck;
import headfirst.MallardDuck;

public class MallarDuckTest {
	
   @Test
   public void test(){
	   Duck mallarDuck=new MallardDuck();
	   mallarDuck.display();
	   mallarDuck.performFly();
	   mallarDuck.performQuack();
   }
}
