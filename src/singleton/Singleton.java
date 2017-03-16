package singleton;
public class Singleton{
	/*
	 * 经典的单例模式
	 */
	private static Singleton uniqueSingleton=null;
	private Singleton(){
		
	}
	public static Singleton getInstance(){
		if(uniqueSingleton==null){
			return new Singleton();
		}
		return uniqueSingleton;
	}
}