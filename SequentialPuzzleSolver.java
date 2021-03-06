import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.*;

public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();
    private List<M> result = null;
    Object lock = new Object();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P pos = puzzle.initialPosition();
        //return search(new PuzzleNode<P, M>(pos, null, null));
        synchronized (lock) {
            new Thread(new Task(new PuzzleNode<P, M>(pos, null, null))).start();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    private class Task implements Runnable{
        PuzzleNode<P, M> node;
        public Task(PuzzleNode<P, M> node) {
            this.node = node;
        }
        @Override
        public void run() {
            synchronized(lock) {
                if (!seen.contains(node.pos) && result == null)
                    seen.add(node.pos);
                else
                    return;
            }
            if (puzzle.isGoal(node.pos)) {
                synchronized (lock) {
                    result = node.asMoveList();
                    lock.notify();
                }
                return;
            }
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);

                PuzzleNode<P, M> child = new PuzzleNode<P, M>(pos, move, node);
                new Thread(new Task(child)).start();
            }
        }
    }

    private List<M> search(PuzzleNode<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);

                /*/
                System.out.print(node.pos.toString());
                System.out.print(" ");
                System.out.println(pos.toString());
                //*/

                PuzzleNode<P, M> child = new PuzzleNode<P, M>(pos, move, node);
                List<M> result = search(child);
                if (result != null)
                    return result;
            }
        }
        return null;
    }


    @Immutable
    static class PuzzleNode<P, M> {
        final P pos;
        final M move;  //which movement makes this position
        final PuzzleNode<P, M> prev;

        PuzzleNode(P pos, M move, PuzzleNode<P, M> prev) {
            this.pos = pos;
            this.move = move;
            this.prev = prev;
        }
        List<M> asMoveList() {
            List<M> solution = new LinkedList<M>();
            for (PuzzleNode<P, M> n = this; n.move != null; n = n.prev)
                solution.add(0, n.move);
            return solution;
        }
    }

    public static void main(String[] args) {
        List s = new SequentialPuzzleSolver<Point1, Move1>(new Graph1()).solve();
        Iterator it = s.iterator();
        System.out.println(1);
        while (it.hasNext()) {
            System.out.println(((Move1)it.next()).next().getPosition() + 1);
        }
    }
}
