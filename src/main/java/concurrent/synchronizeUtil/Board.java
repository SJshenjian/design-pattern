package concurrent.synchronizeUtil;

import java.util.concurrent.CompletionService;
import java.util.concurrent.FutureTask;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/26
 */
// TODO
public class Board {

    public boolean hasConverged() {
        return true;
    }

    public int getMaxX() {
        return 0;
    }

    public int getMaxY() {
        return 0;
    }

    public void waitForConvergence() {
    }

    public void commitNewValues() {
    }

    public Board getSubBoard(int count, int i) {
        return new Board();
    }
}
