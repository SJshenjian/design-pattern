package headfirst.observer.service.test;

import org.junit.Test;

import headfirst.observer.service.impl.CurrentConditionsDisplay;
import headfirst.observer.service.impl.WeatherData;

public class ObserverTest {
	
	@Test
	public void testObserver(){
		WeatherData weatherData=new WeatherData();
	
		CurrentConditionsDisplay currentDisplay=new CurrentConditionsDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 30.4f);
		weatherData.setMeasurements(78, 71, 30.4f);		
	}
}
