package concurrent.mutex;

/**
 * 独享锁
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2019/3/26
 */
public interface Mutex {

    void acquire();

    void release();
}
