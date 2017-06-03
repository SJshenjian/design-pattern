package headfirst.observer.service;

public interface Subject {

	void registerObserver(Observer observer);//注册观察者
	void removeObserver(Observer observer);//移除观察者
	void notifyObservers();//通知观察者
}
