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

        for(int i = 0; i < points.length-1; i++) {
            final Point first = points[i];
            final Point second = points[i+1];
            final double distBetween = Point.distBetween(first, second);

            if(distBetween > length)
                return true;
        }

        return false;
    }

    public static boolean isCondition1(Point[] points, double radius) {        
        for(int i = 0; i + 2 < points.length; i++){
            Point x = points[i];
            Point y = points[i+1];
            Point z = points[i+2];

            double r = Point.circumcircleRadius(x, y, z);
            
            if(r > radius){
                return true;
            }
        }
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
                int end = points.length - q_points + 1;
                int i = 0;
                do {
                    for (int j = 0; j < q_points; j++) {
                        set.add(Point.getQuadrant(points[i + j]));
                    }
                    if (set.size() > quads) {
                        return true;
                    } else {
                        set.clear();
                    }
                    i++;
                } while (i < end);
            }
        }
        return false;
    }

    /**
     * @param points
     * @return true if there are two consecutive coordinates with a negative difference between
     * the x coordinates. i.e X[j] - X [i] < 0, where i = j + 1
     */
    public static boolean isCondition5(Point[] points) {
        if (points.length  >= 2) {
            for (int i = 0; i < points.length - 1; i++) {
                int j = i + 1;
                if (points[j].x - points[i].x < 0) {
                    return true;
                }
            }
            // TODO: add appropriate method parameters
        }
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

    /**
     * @param points array of two dimensional points
     * @param epsilon deviation from pi
     * @param cPts number of separation between a point and the vertex
     * @param dPts number of separation between a point and the vertex
     * @return returns true is LIC 9 is satisfied
     */
    public static boolean isCondition9(Point[] points, double epsilon, int cPts, int dPts) {

        final int SEPARATION = cPts + dPts;

        if(epsilon < 0 || epsilon >= Math.PI)
            return false;

        if(cPts < 1 || dPts < 1)
            return false;

        if(SEPARATION > points.length - 3)
            return false;

        for(int i = 0; i + 2 + SEPARATION <= points.length-1; i++) {
            final int VERTEX_INDEX = i + cPts + 1;
            final int LAST_INDEX = VERTEX_INDEX + dPts + 1;

            Point a = points[i];
            Point vertex = points[VERTEX_INDEX];
            Point b = points[LAST_INDEX];

            if(Point.isEqual(a, vertex) || Point.isEqual(b, vertex))    // angle is undefined
                return false;

            double angle = Point.calculateAngle(a, vertex, b);
            if((angle < (Math.PI - epsilon)) || (angle > (Math.PI + epsilon)))
                return true;
        }

        return false;
    }
    /**
     * 
     * @param e_points one of the number of consecutive data points
     * @param f_points another number of consecutive data points
     * @param area1 the requirement of the area of triangle
     * @param points the input points array
     * @return true if LIC 10 is satisfied
     */
    public static boolean isCondition10(int e_points, int f_points, double area1, Point[] points) {
        if(points.length>=5){
            if(e_points>=1 && f_points>=1 && (e_points+f_points)<=points.length-3){
                int end = points.length - e_points - f_points -3;
                double area;
                for(int i=0;i<end+1;i++){
                    area = Point.calculateArea(points[i], points[i+e_points+1],points[i+e_points+f_points+2]);
                    if(area > area1)
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean isCondition11() {
        // TODO: add appropriate method parameters
        return false;
    }

    /**
     * @param points array of two dimensional points
     * @param kPts separation between consecutive points
     * @param length1 value that distance between points is checked to be greater than
     * @param length2 value that distance between points is checked to be less than
     * @return true if there exists consecutive points, which has a distance between eachother greather than length1 and less than length2
     */
    public static boolean isCondition12(Point[] points, int kPts, int length1, int length2) {

        // check that variable requirements are met
        if(kPts < 1 || kPts > points.length-2 || points.length < 3 || length1 < 0 || length2 < 0)
            return false;

        for(int i = 0; i < (points.length-1-kPts); i++) {
            Point first = points[i];
            Point second = points[i+kPts+1];
            final double distBetween = Point.distBetween(first, second);

            if(distBetween > length1 && distBetween < length2)
                return true;
        } 

        return false;
    }

    public static boolean isCondition13() {
        // TODO: add appropriate method parameters
        return false;
    }
    /**
     * 
     * @param e_points one of the number of consecutive data points
     * @param f_points another number of consecutive data points
     * @param area1 the condition is partly met when area larger than area1
     * @param area2 the condition is partly met when area less than area2
     * @param points the input points array
     * @return true if area between (area1, area2)
     */
    public static boolean isCondition14(int e_points, int f_points, double area1, double area2, Point[] points) {
        if(points.length>=5 && area1 >=0 && area2 > 0){
            if(e_points>=1 && f_points>=1 && (e_points+f_points)<=points.length-3){
                int end = points.length - e_points - f_points -3;
                double area;
                boolean flag1 = false, flag2 = false;
                for(int i=0;i<end+1;i++){
                    area = Point.calculateArea(points[i], points[i+e_points+1],points[i+e_points+f_points+2]);
                    if(area > area1)
                        flag1 = true;
                    if(area < area2)
                        flag2 = true;
                }
                if(flag1 == true && flag2 == true)
                    return true;
            }
        }
        return false;
    }
}
