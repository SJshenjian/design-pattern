package singleton;

public class UserTest {

	 public static void main(String[] args) {
		User user1=User.newInstance();
		user1.setName("马良");
		System.out.println(user1.getName());
		User user2=User.newInstance();
		user2.setName("马良");
		System.out.println(user1.getName());
		if(user1==user2){
			System.out.println("创建的是同一个实例");
		}else{
			System.out.println("分别创建了一个实例");
		}
		
	}
}
