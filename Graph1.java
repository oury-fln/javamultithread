import java.util.HashSet;
import java.util.Set;

public class Graph1 implements Puzzle<Point1, Move1> {
    private int nom ,nop;
    Point1 points[];
    Move1 movements[];
    int matrix[][] = {{0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    public Graph1() {
        nom = 11;
        nop = 10;
        points = new Point1[nop];
        movements = new Move1[nom * 2];
        int i = 0;
        for (i = 0; i < nop - 1; i++) {
            points[i] = new Point1(false, i);
        }
        points[i] = new Point1(true, i);
        //TODO: set the movements
        int k = 0;
        for (i = 0; i < nop; i++) {
            int j = 0;
            for (j = i + 1; j < nop; j++) {
                if (matrix[i][j] == 1) {
                    movements[k++] = new Move1(points[i], points[j]);
                    movements[k++] = new Move1(points[j], points[i]);
                }
            }
        }
    }

    @Override
    public Point1 initialPosition() {
        return points[0];
    }

    @Override
    public boolean isGoal(Point1 position) {
        return position.isGoal();
    }

    @Override
    public Set legalMoves(Point1 position) {
        int i;
        Set moveset = new HashSet();
        for (i = 0; i < nom * 2; i++) {
            if (movements[i].previous().getPosition() == position.getPosition()) {
                moveset.add(movements[i]);
            }
        }
        return moveset;
    }

    @Override
    public Point1 move(Point1 position, Move1 move) {
        return move.next();
    }
}
