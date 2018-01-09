package singleton;

/**
 *  volatile作用：
 *  （1）保持变量更改立即可见性
 *  （2）防止指令重排序
 */
public class DoubleCheckedSingleton {
    private volatile static DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {};

    public static DoubleCheckedSingleton getInstance() {
        if (null == instance) { //变量立即可见
            synchronized(DoubleCheckedSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckedSingleton(); //防止指令重排序
                }
            }
        }
        return instance;
    }
}
