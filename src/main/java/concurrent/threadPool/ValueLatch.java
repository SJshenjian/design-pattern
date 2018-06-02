package concurrent.threadPool;

import concurrent.annotation.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/1
 */
@ThreadSafe
public class ValueLatch<T> {
    private T value = null;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public boolean isSet() {
        return countDownLatch.getCount() == 0;
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            countDownLatch.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        countDownLatch.await();
        synchronized (this) {
            return value;
        }
    }
}
