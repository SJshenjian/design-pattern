package singleton;

public class SimpleSingleton {
    private static SimpleSingleton instance;

    private SimpleSingleton() {};

    public static SimpleSingleton getInstance() {
        if (null == instance) {
            instance = new SimpleSingleton();
        }
        return instance;
    }
}
