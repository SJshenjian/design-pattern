package concurrent.cancelAndShutdown;

import java.util.concurrent.*;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/29
 */
public class Task {
    private final ExecutorService service = Executors.newFixedThreadPool(4);

    public void timeRun(Runnable task, long timeout, TimeUnit unit) {
        Future<?> future = service.submit(task);

        try {
            future.get(timeout, unit);
        } catch (TimeoutException e) {
            // 接下来任务将被取消
        } catch (ExecutionException e) {
            // 重新抛出异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 通过Future实现取消
            future.cancel(true);
        }
    }
}
