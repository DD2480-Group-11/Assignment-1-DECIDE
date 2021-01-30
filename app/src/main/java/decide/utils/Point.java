package decide.utils;

import java.lang.Math;

// immutable standard Point class
public final class Point {
    final public double x;
    final public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param a one of two points
     * @param b one of two points
     * @return the distance between point a and b
     */
    public static double distBetween(Point a, Point b) {
        final double xDiff = a.x - b.x;
        final double yDiff = a.y - b.y;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    /**
     * @param a one out of three points
     * @param vertex The points which forms an angle with point a and b
     * @param b one out of three points
     * @return the angle formed at the vertex by the three points
     */
    public static double calculateAngle(Point a, Point vertex, Point b) {
        Point u = new Point(a.x - vertex.x, a.y - vertex.y);
        Point v = new Point(b.x - vertex.x, b.y - vertex.y);

        double numerator = dotProduct(u, v);
        double denominator = magnitude(u) * magnitude(v);

        return Math.toDegrees(Math.acos((numerator / denominator)));
    }

    /**
     * @param a a two dimensional vector
     * @param b a two dimensional vector
     * @return the dot product of the two vectors
     */
    public static double dotProduct(Point a, Point b) {
        return (a.x * b.x) + (a.y * b.y);
    }

    /**
     * @param p a two dimensional vector
     * @return the magnitude of the given vector
     */
    public static double magnitude(Point p) {
        return Math.sqrt((p.x * p.x) + (p.y * p.y));
    }

    /** 
     * @param a one of two compared two dimensional points
     * @param b one of two compared two dimensional points
     * @return true if the points have the same x and y value
     */
    public static boolean isEqual(Point a, Point b) {
        if((Double.compare(a.x, b.x) == 0) && (Double.compare(a.y, b.y) == 0))
            return true;
        return false;
    }
}