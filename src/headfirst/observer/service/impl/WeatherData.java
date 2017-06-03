package headfirst.observer.service.impl;

import java.util.ArrayList;

import headfirst.observer.service.Observer;
import headfirst.observer.service.Subject;

public class WeatherData implements Subject {

	private ArrayList<Observer> observers;
	private float temperature;//温度
	private float humidity;//湿度
	private float pressure;//压力
	
	public WeatherData(){
		observers=new ArrayList<>();
	}
	
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
	@Override
	public void removeObserver(Observer observer) {
		if(observers.indexOf(observer) > 0){
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObservers() {
		
		for(Observer o:observers){
			o.update(temperature, humidity, pressure);
		}	
	}
	
	public void measurementsChanged(){
		notifyObservers();
	}
	
	public void setMeasurements(float temperature,float humiditym,float pressure){
		this.temperature=temperature;
		this.humidity=humiditym;
		this.pressure=pressure;
		measurementsChanged();
	}

}
