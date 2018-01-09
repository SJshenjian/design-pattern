package singleton;

public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {};

    public synchronized LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
