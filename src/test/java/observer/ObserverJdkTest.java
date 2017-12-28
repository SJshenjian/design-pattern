package observer;

import observer.service.impl.jdk.CurrentConditionsDisplay;
import observer.service.impl.jdk.WeatherData;
import org.junit.Test;

public class ObserverJdkTest {

    @Test
    public void testObserverJdk(){
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(10,20,30);
        weatherData.setMeasurements(10.1f,20.2f,30.3f);
    }
}
