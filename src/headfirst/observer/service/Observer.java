package headfirst.observer.service;

public interface Observer {
	
	/**
	 * 
	 * @param temperature 温度
	 * @param humidity 湿度
	 * @param pressure 气压
	 * @return
	 */
	void update(float temperature,float humidity,float pressure);//数据更新
}
