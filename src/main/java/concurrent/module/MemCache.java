package concurrent.module;

import java.util.concurrent.*;

/**
 * 构建高效且可伸缩的缓存
 *
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
public class MemCache<K, V> implements Computable<K, V> {

    private final ConcurrentHashMap<K, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<K, V> computable;

    public MemCache(Computable<K, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(K key) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(key);
            if (f == null) {
                Callable<V> callable = () -> {
                    return computable.compute(key);
                };
                FutureTask<V> task = new FutureTask<>(callable);
                Future<V> ft = cache.putIfAbsent(key, task);
                if (f == null) {
                    f = ft;
                    task.run();
                }
            }

            try {
                if (f != null) {
                    return f.get();
                }
            } catch (CancellationException e) {
                cache.remove(key, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Computable<Integer, Integer> computable = (key) -> {
            // 模拟费时计算过程
            Thread.sleep(10000);
            return key;
        };
        MemCache<Integer, Integer> memCache = new MemCache<>(computable);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            for (int i = 0; i < 6; i++) {
                new Thread(() -> {
                    try {
                        long startTime = System.currentTimeMillis();
                        memCache.compute(99);
                        long endTime = System.currentTimeMillis();
                        System.out.println("从缓存中取值线程执行时间为：" + (endTime - startTime));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });

        new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                memCache.compute(99);
                long endTime = System.currentTimeMillis();
                System.out.println("未从缓存中取值线程执行时间为：" + (endTime - startTime));
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
