package decide.launch;

import decide.utils.Point;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;

// conditions are used to calculate the CMV
public final class LIC {

    /**
     * @param points array of two dimensional points
     * @param length value that distance between points are checked against
     * @return true if LIC 0 is satisfied
     */
    public static boolean isCondition0(Point[] points, double length) {

        if(length <= 0.0)
            return false;

        for(int i = 0; i < points.length-2; i++) {
            final Point first = points[i];
            final Point second = points[i+1];
            final double distBetween = Point.distBetween(first, second);

            if(distBetween > length)
                return true;
        }

        return false;
    }

    public static boolean isCondition1() {
        // TODO: add appropriate method parameters
        return false;
    }

    /**
     * @param points array of two dimensional points
     * @param epsilon deviation from pi
     * @return true if LIC 2 is satisfied
     */
    public static boolean isCondition2(Point[] points, double epsilon) {

        if(epsilon < 0 || epsilon >= Math.PI)
            return false;

        for(int i = 0; i + 2 <= points.length-1; i++) {
            Point a = points[i];
            Point vertex = points[i+1];
            Point b = points[i+2];

            if(Point.isEqual(a, vertex) || Point.isEqual(b, vertex))    // angle is undefined
                return false;

            double angle = Point.calculateAngle(a, vertex, b);
            if((angle < (Math.PI - epsilon)) || (angle > (Math.PI + epsilon)))
                return true;
        }

        return false;
    }

    public static boolean isCondition3(double area1,Point[] points) {
        // TODO: add appropriate method parameters
        double area;

        for(int i=0;i<points.length-2;i++) {
            area = Point.calculateArea(points[i],points[i+1],points[i+2]);
            if(area > area1)
                return true;
        }
        return false;
    }

    /**
     *
     * @param q_points, number of consecutive data points
     * @param quads, quads + 1 gives the number of consecutive quadrants with consecutive points
     * @param points, the points
     * @return true if there exists q_points consecutive data points such that consecutive
     * points lie in more than @param quads quadrants, otherwise false
     */
    public static boolean isCondition4(int q_points, int quads, Point[] points) {
        if (2 <= q_points && q_points <= points.length) {
            if (1 <= quads && quads <= 3) {
                Set<Integer> set = new HashSet<>();
                int end;
                if (points.length == q_points) { // end is needed in this case because if qpoints== points.length
                    end = points.length;         // then main loop does not run
                } else {
                    end = points.length - q_points;
                }
                for (int i = 0; i < end; i++) {
                    for (int j = 0; j < q_points; j++) {
                        set.add(Point.getQuadrant(points[i + j]));
                    }
                    if (set.size() > quads) {
                        return true;
                    } else {
                        set.clear();
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCondition5() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition6() {
        // TODO: add appropriate method parameters
        return false;
    }


    public static boolean isCondition7() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition8() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition9() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition10() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition11() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition12() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition13() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition14() {
        // TODO: add appropriate method parameters
        return false;
    }
}
