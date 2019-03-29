package concurrent.mutex;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2019/3/26
 */
public class Main {

    public static void main(String[] args) {
        Mutex mutex = new MutexImpl();
        for (int i = 0; i < 10; i++) {
            new Thread(new MutexThread(mutex)).start();
        }
    }
}

class MutexThread implements Runnable {

    private Mutex mutex;

    public MutexThread(Mutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        mutex.acquire();
        System.out.println(name + "获得锁开始执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            System.out.println(name + "释放锁结束运行");
        }
    }
}