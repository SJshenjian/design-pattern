package concurrent.test;

import java.util.concurrent.Semaphore;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/28
 */
public class BoundedBuffer<E> {
    private final Semaphore availableItem, avaialbleSpace;
    private final E[] items;
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity) {
        availableItem = new Semaphore(0);
        avaialbleSpace = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItem.availablePermits() == 0;
    }

    public boolean isFull() {
        return avaialbleSpace.availablePermits() == 0;
    }

    public void put(E item) throws InterruptedException {
        avaialbleSpace.acquire();
        doInsert(item);
        availableItem.release();
    }

    public E take() throws InterruptedException {
        availableItem.acquire();
        E item = doExtract();
        avaialbleSpace.release();
        return item;
    }

    public synchronized void doInsert(E item) {
        int i = putPosition;
        items[i] = item;
        putPosition = (++i == items.length ? 0 : i);
    }

    public synchronized E doExtract() {
        int i = takePosition;
        E item = items[i];
        items[i] = null;
        takePosition = (++i == items.length ? 0 : i);
        return item;
    }
}
