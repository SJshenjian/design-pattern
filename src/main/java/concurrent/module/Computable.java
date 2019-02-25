package concurrent.module;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
@FunctionalInterface
public interface Computable<K, V> {

    V compute(K key) throws InterruptedException;
}
