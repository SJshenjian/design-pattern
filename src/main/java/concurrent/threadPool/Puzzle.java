package concurrent.threadPool;

import java.util.Set;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/1
 */
public interface Puzzle<P, M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position, M move);
}
