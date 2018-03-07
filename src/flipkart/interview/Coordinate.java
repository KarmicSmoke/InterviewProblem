package flipkart.interview;

public class Coordinate {

    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public double getDistance(Coordinate coordinate) {
        double a = coordinate.getX();
        double b = coordinate.getY();

        return Math.sqrt((x - a) * (x - a) + (y - b) * (y - b));
    }

}
