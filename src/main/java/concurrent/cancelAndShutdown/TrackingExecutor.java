package concurrent.cancelAndShutdown;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/29
 */
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService service = Executors.newFixedThreadPool(4);
    private final Set<Runnable> taskCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void shutdown() {
        service.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return service.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return service.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return service.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return service.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(final Runnable runnable) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()) {
                        taskCancelledAtShutdown.add(runnable);
                    }
                }
            }
        });
    }

    public List<Runnable> getCancelledTasks() {
        if (!service.isTerminated()) {
            throw new IllegalArgumentException();
        }
        return new ArrayList<Runnable>(taskCancelledAtShutdown);
    }

}
