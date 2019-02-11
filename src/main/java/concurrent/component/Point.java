package concurrent.component;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/24
 */
@Immutable
public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
