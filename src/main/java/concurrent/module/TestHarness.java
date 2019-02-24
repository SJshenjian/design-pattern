package concurrent.module;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁实现统计多个线程执行时间
 *
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
public class TestHarness {

   public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
       CountDownLatch startGate = new CountDownLatch(1);
       CountDownLatch endGate = new CountDownLatch(nThreads);

       for (int i = 0 ; i < nThreads; i++) {
           new Thread(() -> {
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
           }).start();
       }

       long startTime = System.nanoTime();
       startGate.countDown();
       endGate.await();
       long endTime = System.nanoTime();
       return endTime - startTime;
   }

    public static void main(String[] args) throws InterruptedException {
       Runnable runnable = () -> {
           for (int i = 0; i < 10000; i++) {

           }
       };
        long time = new TestHarness().timeTasks(4, runnable);
        System.out.println(time);
    }
}
