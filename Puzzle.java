import java.util.Set;

public interface Puzzle<Point1, Move1> {
    //initialize the position
    Point1 initialPosition();
    //if the position is the goal
    boolean isGoal(Point1 position);
    //a set of legal movement
    Set<Move1> legalMoves(Point1 position);
    //one position move to the next one
    Point1 move(Point1 position, Move1 move);
}
