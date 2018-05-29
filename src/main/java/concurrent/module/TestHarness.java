package concurrent.module;

import java.util.concurrent.CountDownLatch;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end =System.nanoTime();
        return end - start;
    }
}
