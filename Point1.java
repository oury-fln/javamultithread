public class Point1 {
    private boolean isGoal;
    private int position;
    public Point1(boolean isGoal, int position) {
        this.isGoal = isGoal;
        this.position = position;
    }
    public boolean isGoal() { return isGoal; }
    public int getPosition() { return position; }
    @Override
    public String toString() {
        return Integer.toString(position);
    }
}
