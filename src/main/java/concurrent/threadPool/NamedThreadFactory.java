package concurrent.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义ThreadFactory
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2019/5/29
 */
public class NamedThreadFactory implements ThreadFactory {

    private static AtomicInteger poolId;
    private static ThreadGroup threadGroup;
    private static AtomicInteger threadId;
    private static String threadNamePrefix = "NamedThreadPool";

    public NamedThreadFactory() {
        poolId = new AtomicInteger();
        threadGroup = new ThreadGroup("NamedThreadFactory");
        threadId = new AtomicInteger();
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = threadNamePrefix + "-pool-" + poolId + "-thread-" + threadId.getAndIncrement();
        Thread thread = new Thread(threadGroup, r, name);
        return thread;
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 1000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
        threadPoolExecutor.setThreadFactory(new NamedThreadFactory());

        Lock lockOne = new ReentrantLock();
        Lock lockTwo = new ReentrantLock();

        threadPoolExecutor.execute(() -> {
            lockOne.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockTwo.lock();
        });

        threadPoolExecutor.execute(() -> {
            lockTwo.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockOne.lock();
        });
    }
}
