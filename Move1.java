public class Move1 {
    private Point1 source, destination;
    public Move1(Point1 source, Point1 destination) {
        this.source = source;
        this.destination = destination;
    }
    public Point1 next() {
        return destination;
    }
    public Point1 previous() {
        return source;
    }

}
