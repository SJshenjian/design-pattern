package concurrent.module;

import java.util.concurrent.*;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
public class Memoizer<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memoizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while(true) {
            Future<V> f = cache.get(arg);
            if (null == f) {
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V> futureTask = new FutureTask<V>(callable);
                f = cache.putIfAbsent(arg, futureTask);
                if (null == f) {
                    f = futureTask;
                    futureTask.run();
                }
            }

            try {
                return f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
