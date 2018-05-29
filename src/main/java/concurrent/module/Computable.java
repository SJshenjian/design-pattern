package concurrent.module;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
