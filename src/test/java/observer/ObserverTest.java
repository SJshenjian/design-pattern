package observer;

import observer.service.impl.CurrentConditionsDisplay;
import observer.service.impl.WeatherData;
import org.junit.Test;

public class ObserverTest {

    @Test
    public void testObserver() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(10,20,30);
        weatherData.setMeasurements(10.1f,20.1f,30.1f);
    }
}
