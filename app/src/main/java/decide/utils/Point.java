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

        return Math.acos((numerator / denominator));
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

    /**
     * Function name: calculateArea
     * <p>
     * Using Heron's formula to calculate the area of triangle.
     *
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return the area of the triangle
     * @author Yu Zhou
     */
    public static double calculateArea(Point a, Point b, Point c) {
        double lengthA = Point.distBetween(a, b);
        double lengthB = Point.distBetween(b, c);
        double lengthC = Point.distBetween(c, a);
        double halfPerimeter = (lengthA + lengthB + lengthC)/2.0;
        double Area3 = halfPerimeter*(halfPerimeter-lengthA)*(halfPerimeter-lengthB)*(halfPerimeter-lengthC);

        if(Area3<0 || Area3==0){
            return 0.0;
        }

        double SquareRootOfArea3 = Math.sqrt(Area3);
        return SquareRootOfArea3;
    }

    /**
     * Calculates the cartesian quadrant of point p.
     * @param p the point that should be checked
     * @return the quadrant number, ranging from 1-4
     */
    public static int getQuadrant(Point p) {
        if (p.x >= 0.0 && p.y >= 0.0) {
            return 1;
        } else if (p.x < 0.0 && p.y >= 0.0) {
            return 2;
        } else if (p.x <= 0.0 && p.y < 0.0) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Calculates the radius of the circumcircle of three points
     * @param x First point
     * @param y Second point
     * @param z Third point
     * @return the radius of the circumcircle of the three points
     */
    public static double circumcircleRadius(Point x, Point y, Point z){
        double a = distBetween(x, y);
        double b = distBetween(x, z);
        double c = distBetween(y, z);
        
        double radius = (a * b * c) / Math.sqrt( (a + b + c) * (b + c - a) * (c + a - b) * (a + b - c));

        return radius;
    }

    /**
     * Checks if three points are collinear (forms a line)
     * @param a Point
     * @param b Point
     * @param c Point
     * @return true if collinear
     */
    public static boolean collinearPoints(Point a, Point b, Point c){
        double slopeAB = (b.y - a.y) / (b.x - a.x);
        double slopeBC = (c.y - b.y) / (c.x - b.x);
        double slopeAC = (c.y - a.y) / (c.x - a.x);
        
        if( Double.compare(slopeAB, slopeBC) == 0 || 
            Double.compare(slopeAB, slopeAC) == 0 || 
            Double.compare(slopeBC, slopeAC) == 0){
                return true;
            }
        return false;
    }

    /**
     * Calculates the length of a line formed by three points
     * @param a Point
     * @param b Point
     * @param c Point
     * @return the length of the line formed by the three points
     */
    public static double lineLength(Point a, Point b, Point c){
        double distAB = distBetween(a, b);
        double distAC = distBetween(a, c);
        double distBC = distBetween(b, c);

        double result = Math.max(Math.max(distAB,distAC), distBC);

        return result;
    }
}
