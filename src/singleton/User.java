package singleton;

public class User {

	private String name;
	
	private User(){}
	
	/*
	 * 懒汉式单例类，在第一次调用时进行实例化
	 */
	private static User user=null;
	public static User newInstance(){
		if(user==null){
			user=new User();
		}
		return user;
	}
	
	
	/*
	 * 饿汉式单例类，在初始化时进行实例化
	 
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
