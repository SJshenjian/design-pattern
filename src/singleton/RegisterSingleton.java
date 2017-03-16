package singleton;

import java.util.HashMap;
import java.util.Map;

/*
 * 登记式单例
 * 类似spring里面的方法，将类名注册，下次直接获取
 */

public class RegisterSingleton {
	private static HashMap<String, Object> map = new HashMap<String, Object>();

	protected RegisterSingleton(){};
	
	public static Object getInstance(String name) {
		
		if (map.get(name) == null) {
			try {
				map.put(name, Class.forName(name).newInstance());
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(name);
	}
	
	public String helloWorld(){
		return "Hello World";
	}

	public static void main(String[] args) {
		RegisterSingleton rs=(RegisterSingleton) RegisterSingleton.getInstance("singleton.RegisterSingleton");
		RegisterSingleton rs2=(RegisterSingleton) RegisterSingleton.getInstance("singleton.RegisterSingleton");	
		System.out.println(rs.helloWorld());
		
		if(rs == rs2){
			System.out.println("创建的是同一个实例");
		}else{
			System.out.println("分别创建了一个实例");
		}
	}
}