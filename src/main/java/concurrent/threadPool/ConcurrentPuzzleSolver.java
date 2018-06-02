package concurrent.threadPool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/1
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService service = Executors.newFixedThreadPool(6);
    private final ConcurrentMap<P, Boolean> seen = new ConcurrentHashMap<>();
    private final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();
    private final AtomicInteger taskCount = new AtomicInteger(0);

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() throws InterruptedException {
        try {
            P pos = puzzle.initialPosition();
            service.execute(newTask(pos, null, null));
            // 阻塞直到找到答案
            Node<P, M> node = solution.getValue();
            return node != null ? node.asMoveList() : null;
        } finally {
            service.shutdown();
        }
    }

    private Runnable newTask(P pos, M move, Node<P, M> node) {
        return new SolverTask(pos,move, node);
    }

    class SolverTask extends Node<P, M> implements Runnable {
        final P pos;
        final M move;
        final Node<P, M> prev;

        SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
            this.pos = pos;
            this.move = move;
            this.prev = prev;
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
                    return; // 已经找到了解答或者已经遍历了该位置
                }
                if (puzzle.isGoal(pos)) {
                    solution.setValue(this);
                } else {
                    for (M m : puzzle.legalMoves(pos)) {
                        service.execute(newTask(puzzle.move(pos, m), m, this));
                    }
                }
            } finally {
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }


    static class Node<P, M> {
        final P pos;
        final M move;
        final Node<P, M> prev;

        Node(P pos, M move, Node<P, M> prev) {
            this.pos = pos;
            this.move = move;
            this.prev = prev;
        }

        List<M> asMoveList() {
            List<M> solution = new LinkedList<>();
            for (Node<P, M> n = this; n.move != null; n = n.prev) {
                solution.add(0, n.move);
            }
            return solution;
        }
    }
}
