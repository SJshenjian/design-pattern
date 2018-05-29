package concurrent.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/29
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {
            /** 允许线程退出 **/
        }
    }

    public void cancel() {
        interrupt();
    }
}
