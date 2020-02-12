package singleton;

/**
 * 枚举方式实现单例模式最为推荐
 * 其他实现方式无法解决序列化反序列化单实例问题，
 * 枚举保证对象序列化到文件，在反序列化后得到的是同一个对象
 *
 * @author Jian Shen
 * @version V1.0.0
 * @date 2020/2/12
 */
public class EnumSingleton {

    public enum ResourceInstance {
        INSTANCE;
        private Resource resource;

        ResourceInstance() {
            this.resource = new Resource();
        }

        public Resource getResource() {
            return this.resource;
        }
    }

    public static void main(String[] args) {
        Resource resourceOne = ResourceInstance.INSTANCE.getResource();
        Resource resourceTwo = ResourceInstance.INSTANCE.getResource();
        System.out.println(resourceOne == resourceTwo);
    }
}

class Resource {

}
