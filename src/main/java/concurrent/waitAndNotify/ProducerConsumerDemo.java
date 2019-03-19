package concurrent.waitAndNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * wait/notifyAll实现生产者消费者
 *
 * 1) wait/notify(All)可用于线程间(线程数量>3)通信
 * 2) 永远在synchronized方法或对象里使用wait/notify(All),不然JVM报java.lang.IllegalMonitorStateException
 * 3) 永远在while循环里使用wait. 原因见代码中注释
 * 4) 永远在线程间共享对象(生产者消费者中为缓冲区队列)上使用wait/notify(All)
 * 5) 多线程间协作，更倾向于使用notifyAll，唤醒在该对象上等待的全部线程
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2019/3/19
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;

        Thread producer = new Thread(new Producer(queue, maxSize));
        producer.setName("Producer");

        Thread consumerOne = new Thread(new Consumer(queue, maxSize));
        consumerOne.setName("ConsumerOne");

        Thread consumerTwo = new Thread(new Consumer(queue, maxSize));
        consumerTwo.setName("ConsumerTwo");

        producer.start();
        consumerOne.start();
        consumerTwo.start();
    }
}

class Producer implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("Queue is full, Producer " + Thread.currentThread().getName() + " waiting");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = random.nextInt();
                System.out.println("Producer " + value);
                queue.add(value);
                queue.notifyAll();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Consumer implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                // while不可改为if,如果改为if，则可能抛出java.util.NoSuchElementException
                // 当前等待的线程被唤醒时，但是由于其他消费者刚好消费完，使得队列为空
                // 此时如果不重新判断队列为空，代码继续向下执行queue.remove势必抛出java.util.NoSuchElementException
                // 若放入while循环中重新判断条件，若条件不满足，线程则继续挂起，无影响
                while (queue.isEmpty()) {
                    try {
                        System.out.println("Queue is empty, Consumer " + Thread.currentThread().getName() + " waiting");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " consume " + queue.remove());
                queue.notifyAll();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
