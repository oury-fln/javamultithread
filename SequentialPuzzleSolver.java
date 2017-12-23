import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P pos = puzzle.initialPosition();
        return search(new PuzzleNode<P, M>(pos, null, null));
    }

    private List<M> search(PuzzleNode<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);
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
}
