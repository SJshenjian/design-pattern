package singleton;

public class User {

	private String name;
	
	private User(){}
	
	/*
	 * 懒汉式单例类，在第一次调用时进行实例化
	 */
	//懒汉线程安全实现
	/*
	 * //1.在newInstance方法上加同步
	private static User user=null;
	public static synchronized User newInstance(){
		if(user==null){
			user=new User();
		}
		return user;
	}*/
	/*
	//2  双重检查锁定   不是线程安全的，如果要用这种方式，需要使用volatile关键字
	private static volatile User user=null;
	public static User newInstance(){
		if(user==null){
			synchronized(User.class){
				if(user==null){
					return new User();
				}
			}
		}
		return user;
	}*/
	
	//3.静态内部类
	private static class LazyHolder{
		private static final User USER=new User();
	}
	
	public static User newInstance(){
		return LazyHolder.USER;
	}
	
	/*
	 * 饿汉式单例类，在初始化时进行实例化,天生线程安全
	 
	private static final User user=new User();
	public static User getUser(){
		return user;
	}*/
    
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
}
