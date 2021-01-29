package decide.launch;

import decide.utils.Point;

// conditions are used to calculate the CMV
public final class LIC {

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

    public static boolean isCondition2() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition3() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition4() {
        // TODO: add appropriate method parameters
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