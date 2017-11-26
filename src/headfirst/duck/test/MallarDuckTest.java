package headfirst.duck.test;

import static org.junit.Assert.*;

import org.junit.Test;

import headfirst.duck.Duck;
import headfirst.duck.MallardDuck;

public class MallarDuckTest {
	
   @Test
   public void test(){
	   Duck mallarDuck=new MallardDuck();
	   assertEquals("I'm a real MallardDuck",mallarDuck.display());
	   assertEquals("I'm flying!",mallarDuck.performFly());
	   assertEquals("Quack",mallarDuck.performQuack());  
   }
}
