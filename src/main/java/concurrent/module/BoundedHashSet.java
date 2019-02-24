package concurrent.module;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 *
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T e) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(e);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }
}
