import java.util.Set;

public class Graph1 implements Puzzle<Point1, Move1> {
    Point1 points[] = new Point1[10];
    Move1 movements[] = new Move1[15];
    public Graph1() {
        int i = 0;
        for (i = 0; i < 9; i++) {
            points[i] = new Point1(false, i);
        }
        points[i] = new Point1(true, i);
        //TODO: set the movements
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
        //TODO: set the position's movements into a hashset
        return null;
    }

    @Override
    public Point1 move(Point1 position, Move1 move) {
        //TODO:???
        return null;
    }
}
