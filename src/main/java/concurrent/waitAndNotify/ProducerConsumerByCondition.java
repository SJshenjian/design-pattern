package concurrent.waitAndNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock + Condition实现生产者消费者
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2019/3/29
 */
public class ProducerConsumerByCondition {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        final Lock lock = new ReentrantLock();
        final Condition notEmpty = lock.newCondition();
        final Condition notFull = lock.newCondition();

        Thread producer = new Thread(new ProducerByCondition(queue, maxSize, lock, notEmpty, notFull));
        producer.setName("Producer");

        Thread consumerOne = new Thread(new ConsumerByCondition(queue, maxSize, lock, notEmpty, notFull));
        consumerOne.setName("ConsumerOne");

        Thread consumerTwo = new Thread(new ConsumerByCondition(queue, maxSize, lock, notEmpty, notFull));
        consumerTwo.setName("ConsumerTwo");

        producer.start();
        consumerOne.start();
        consumerTwo.start();
    }
}

class ProducerByCondition implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;
    final Lock lock;
    final Condition notEmpty;
    final Condition notFull;

    public ProducerByCondition(Queue<Integer> queue, int maxSize, Lock lock, Condition notEmpty, Condition notFull) {
        this.queue = queue;
        this.maxSize = maxSize;
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            lock.lock();
            try {
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("Queue is full, Producer " + Thread.currentThread().getName() + " waiting");
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = random.nextInt();
                System.out.println("Producer " + value);
                queue.add(value);
                notEmpty.signalAll();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

class ConsumerByCondition implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;
    final Lock lock;
    final Condition notEmpty;
    final Condition notFull;

    public ConsumerByCondition(Queue<Integer> queue, int maxSize, Lock lock, Condition notEmpty, Condition notFull) {
        this.queue = queue;
        this.maxSize = maxSize;
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("Queue is empty, Consumer " + Thread.currentThread().getName() + " waiting");
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " consume " + queue.remove());
                notFull.signalAll();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
