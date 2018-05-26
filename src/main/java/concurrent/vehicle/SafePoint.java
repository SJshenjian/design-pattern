package concurrent.vehicle;

import concurrent.annotation.ThreadSafe;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/24
 */
@ThreadSafe
public class SafePoint {
    private int x;
    private int y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint safePoint) {
        this(safePoint.x, safePoint.y);
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }
}
