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

    public static double distBetween(Point a, Point b) {
        final double xDiff = a.x - b.x;
        final double yDiff = a.y - b.y;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }
}