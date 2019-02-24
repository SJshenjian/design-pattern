package concurrent.module;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2019/2/24
 */
public class CyclicBarrierWorker {

    class Worker implements Runnable {

        private int id;
        private CyclicBarrier cyclicBarrier;

        public Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "已到达栅栏处");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("所有线程已到达栅栏处, 2333");
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierWorker().new Worker(i, cyclicBarrier)).start();
        }
    }
}
