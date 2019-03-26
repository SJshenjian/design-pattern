package concurrent.mutex;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2019/3/26
 */
public class MutexImpl implements Mutex {

    private Sync sync = new Sync();

    @Override
    public void acquire() {
        sync.acquire(1);
    }

    @Override
    public void release() {
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            return super.compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.compareAndSetState(1, 0);
        }
    }
}
